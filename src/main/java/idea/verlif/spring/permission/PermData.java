package idea.verlif.spring.permission;

import java.util.Set;

/**
 * 权限数据接口
 *
 * @param <T> 角色对象泛型
 * @author Verlif
 * @version 1.0
 * @date 2022/1/25 16:28
 */
public interface PermData<T> {

    /**
     * 获取角色组
     *
     * @return 角色组集合
     */
    Set<T> getRoles();

    /**
     * 获取关键词组
     *
     * @return 关键词集合
     */
    Set<String> getKeys();
}
