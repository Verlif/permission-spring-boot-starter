package idea.verlif.spring.permission.impl;

import idea.verlif.spring.permission.PermData;
import idea.verlif.spring.permission.PermissionDetector;

/**
 * @author Verlif
 * @version 1.0
 * @date 2021/11/9 12:29
 */
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

    @Override
    public PermData<Object> getRequestData() {
        return null;
    }
}
