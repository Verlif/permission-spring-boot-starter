# Permission

接口权限控制  
轻量级的访问权限控制，一个注解与一个接口即可完成权限控制。  

本组件**适用**于以下项目:

* 需要对不同的用户做不同的权限控制
* 需要对接口做组合权限判定

**不适用**于以下项目（推荐使用 [接口限制器](https://github.com/Verlif/limit-spring-boot-starter) ）:

* 没有登录系统
* 没有角色等访问者身份区分方式

*注：完整注释请clone本项目*

## 添加

1. 添加Jitpack仓库源

> maven
> ```xml
> <repositories>
>    <repository>
>        <id>jitpack.io</id>
>        <url>https://jitpack.io</url>
>    </repository>
> </repositories>
> ```

2. 添加依赖

> maven
> ```xml
>    <dependencies>
>        <dependency>
>            <groupId>com.github.Verlif</groupId>
>            <artifactId>permission-spring-boot-starter</artifactId>
>            <version>2.6.6-0.2</version>
>        </dependency>
>    </dependencies>
> ```

3. 启用服务

在任意配置类上使用`@EnablePermission`注解启用接口权限控制

## 使用

### 实现`PermissionHandler`接口（可选）

`PermissionHandler`是权限处理接口，用于对权限无法通过的处理。  
内置的实现类会抛出`NoPermDataException`与`NoPermissionException`异常，可以通过 [全局异常处理](https://github.com/Verlif/exception-spring-boot-starter) 组件来处理这些异常，
也可以通过构造实现类来完成自定义逻辑。实现接口后，需要加上`@Component`来注入到Bean池，这样权限控制器才可以找到它。

```java
public class DefaultPermissionHandler implements PermissionHandler {

    @Override
    public Object onNoPermData() {
        throw new NoPermDataException();
    }

    @Override
    public Object onNoPermission(PermData<?> data, Perm perm, Method method) {
        throw new NoPermissionException();
    }

}
```

### 实现`PermissionDetector`接口（**必选**）

`PermissionDetector`是权限判定接口，是权限判定核心。内置的实现类实现了基础的权限判定，但未实现权限数据的获取。  
开发者**必须**实现自己的判定实例。实现后与`PermissionHandler`相同，需要自定注入Bean池中。

```java
public class DefaultPermissionDetector implements PermissionDetector<Object> {

    public DefaultPermissionDetector() {
    }

    @Override
    public boolean hasRole(PermData<Object> data, Object role) {
        return data.getRoles().stream().anyMatch(o -> o == role);
    }

    @Override
    public boolean hasKey(PermData<Object> data, String key) {
        return data.getKeys().stream().anyMatch(s -> s.equals(key));
    }

    /**
     * 主要实现以下方法，这个方法用于获取当前的访问者权限数据
     **/
    @Override
    public PermData<Object> getRequestData() {
        return null;
    }
}
```

`PermData`是一个权限数据接口。一般的应用场景是让项目中的`User`去实现这个接口，然后在上面的`getRequestData()`方法中返回访问者的`User`对象。

## 用他！

`@Perm`注解用于API接口上或是`Controller`类上。

```java
    /**
     * 获取个人信息时，需要登录用户
     */
    @Perm(hasRole = "user")
    @Operation(summary = "获取个人信息")
    @GetMapping("/self")
    public BaseResult<?> selfInfo() {
        return userBiz.getSelfInfo();
    }
```

## 配置

权限配置属性如下：
```yaml
station:
  # 权限功能配置
  permission:
    # 权限功能是否开启
    enable: true
```

