package idea.verlif.spring.permission.anno;

import idea.verlif.spring.permission.PermissionAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/1/25 16:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Configuration
@Documented
@Import({PermissionAspect.class})
public @interface EnablePermission {
}
