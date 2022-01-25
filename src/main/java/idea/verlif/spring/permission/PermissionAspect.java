package idea.verlif.spring.permission;

import idea.verlif.spring.permission.anno.Perm;
import idea.verlif.spring.permission.exception.NoPermDataException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 接口入参检测
 *
 * @author Verlif
 * @version 1.0
 * @date 2021/11/10 10:46
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "station.permission", value = "enable", matchIfMissing = true)
@Import(PermissionConfig.class)
public class PermissionAspect {

    @Autowired
    private PermissionDetector permissionDetector;

    @Autowired
    private PermissionHandler permissionHandler;

    public PermissionAspect() {
    }

    @Around("@within(idea.verlif.spring.permission.anno.Perm) || @annotation(idea.verlif.spring.permission.anno.Perm)")
    public Object dsPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature sig = joinPoint.getSignature();
        Method method = ((MethodSignature) sig).getMethod();

        // 检测方法上的权限标记
        Perm perm = method.getAnnotation(Perm.class);
        if (perm == null) {
            // 检测类上的权限标记
            perm = method.getDeclaringClass().getAnnotation(Perm.class);
        }
        if (perm != null) {
            Object o = validatePerm(perm, method);
            if (o != null) {
                return o;
            }
        }
        return joinPoint.proceed();
    }

    private Object validatePerm(Perm perm, Method method) {
        PermData<Object> user = permissionDetector.getRequestData();
        if (user == null) {
            throw new NoPermDataException();
        }
        if (perm.hasKey().length() > 0 && !permissionDetector.hasKey(user, perm.hasKey())) {
            return permissionHandler.onNoPermission(user, perm, method);
        } else if (perm.hasRole().length() > 0 && !permissionDetector.hasRole(user, perm.hasRole())) {
            return permissionHandler.onNoPermission(user, perm, method);
        } else if (perm.noRole().length() > 0 && !permissionDetector.noRole(user, perm.noRole())) {
            return permissionHandler.onNoPermission(user, perm, method);
        } else if (perm.noKey().length() > 0 && !permissionDetector.noKey(user, perm.noKey())) {
            return permissionHandler.onNoPermission(user, perm, method);
        }
        return null;
    }
}
