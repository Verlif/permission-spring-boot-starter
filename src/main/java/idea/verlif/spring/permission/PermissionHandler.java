package idea.verlif.spring.permission;

import idea.verlif.spring.permission.anno.Perm;

import java.lang.reflect.Method;

/**
 * 权限处理器，用于对权限无法通过的处理
 *
 * @author Verlif
 * @version 1.0
 * @date 2022/1/25 16:41
 */
public interface PermissionHandler {

    /**
     * 没有权限数据体时触发
     *
     * @return 返回给前端的值
     */
    Object onNoPermData();

    /**
     * 没有权限时触发。
     *
     * @param data   权限体对象
     * @param perm   当前使用的权限注解
     * @param method 当前判定的方法
     * @return 返回给前端的值
     */
    Object onNoPermission(PermData<?> data, Perm perm, Method method);
}
