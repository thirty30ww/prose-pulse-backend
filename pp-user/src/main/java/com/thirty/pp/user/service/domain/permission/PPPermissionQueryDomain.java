package com.thirty.pp.user.service.domain.permission;

import java.util.List;

public interface PPPermissionQueryDomain  {
    /**
     * 根据角色ID列表获取权限ID列表
     * <p>
     * 包括当前角色的所有子角色的权限
     * <p>
     * 如果角色的level为0，则拥有所有权限
     *
     * @param roleIds 角色ID列表
     * @return 权限ID列表
     */
    List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds);
}