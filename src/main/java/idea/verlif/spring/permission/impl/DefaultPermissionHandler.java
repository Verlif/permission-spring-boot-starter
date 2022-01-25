package idea.verlif.spring.permission.impl;

import idea.verlif.spring.permission.PermData;
import idea.verlif.spring.permission.PermissionHandler;
import idea.verlif.spring.permission.anno.Perm;
import idea.verlif.spring.permission.exception.NoPermDataException;
import idea.verlif.spring.permission.exception.NoPermissionException;

import java.lang.reflect.Method;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/1/25 16:43
 */
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
