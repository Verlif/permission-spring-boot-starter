package idea.verlif.spring.permission;

/**
 * 权限判定器，对接口进行权限检测
 *
 * @param <T> 权限数据中的角色对象泛型
 * @author Verlif
 * @version 1.0
 * @date 2021/11/9 11:28
 */
public interface PermissionDetector<T> {

    /**
     * 权限数据是否拥有角色
     *
     * @param data 权限数据，从下面的{@link #getRequestData()}获取的权限数据
     * @param role @Perm的hasRole参数
     * @return 权限数据是否拥有角色
     */
    boolean hasRole(PermData<T> data, T role);

    /**
     * 权限数据是否拥有权限
     *
     * @param data 权限数据，从下面的{@link #getRequestData()}获取的权限数据
     * @param key  @Perm的hasKey参数
     * @return 权限数据是否拥有权限
     */
    boolean hasKey(PermData<T> data, String key);

    /**
     * 与{@code hasRole(String)}方法相反
     *
     * @param data 判定对象
     * @param role 角色名称
     * @return true - 没有；false - 拥有
     * @see #hasRole(PermData, Object)
     */
    default boolean noRole(PermData<T> data, T role) {
        return !hasRole(data, role);
    }

    /**
     * 与{@code hasKey(String)}方法相反
     *
     * @param data 判定对象
     * @param key  关键词
     * @return true - 没有；false - 拥有
     * @see #hasKey(PermData, String)
     */
    default boolean noKey(PermData<T> data, String key) {
        return !hasKey(data, key);
    }

    /**
     * 获取当前的请求数据对象
     *
     * @return 权限数据对象。一般情况下是返回实现了这个接口的用户对象
     */
    PermData<T> getRequestData();
}
