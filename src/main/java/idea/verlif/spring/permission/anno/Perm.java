package idea.verlif.spring.permission.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问权限配置。<br/>
 * 所有的参数可同时存在，且都会参与判定。有一项不通过，则判定为无权限。
 *
 * @author Verlif
 * @version 1.0
 * @date 2021/11/11 10:50
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Perm {

    /**
     * 拥有角色时可访问
     *
     * @return 需要的角色
     */
    String hasRole() default "";

    /**
     * 拥有关键词时可访问
     *
     * @return 需要的关键词
     */
    String hasKey() default "";

    /**
     * 没有角色时可访问
     *
     * @return 不允许的角色
     */
    String noRole() default "";

    /**
     * 没有关键词时可访问
     *
     * @return 不允许的关键词
     */
    String noKey() default "";
}
