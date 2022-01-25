package idea.verlif.spring.permission;

import idea.verlif.spring.permission.impl.DefaultPermissionDetector;
import idea.verlif.spring.permission.impl.DefaultPermissionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限配置
 *
 * @author Verlif
 * @version 1.0
 * @date 2021/12/17 16:10
 */
@Configuration
public class PermissionConfig {

    @Bean
    @ConditionalOnMissingBean(PermissionDetector.class)
    public PermissionDetector<?> permissionDetector() {
        return new DefaultPermissionDetector();
    }

    @Bean
    @ConditionalOnMissingBean(PermissionHandler.class)
    public PermissionHandler permissionHandler() {
        return new DefaultPermissionHandler();
    }
}
