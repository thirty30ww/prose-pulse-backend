package com.thirty.pp.user.service.domain.permission.impl;

import com.thirty.pp.user.service.domain.permission.PPPermissionQueryDomain;
import com.thirty.pp.user.service.domain.permission.PPPermissionValidationDomain;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.List;

public class PPPermissionValidationDomainImpl  implements PPPermissionValidationDomain {

    @Resource
    private PPPermissionQueryDomain ppPermissionQueryDomain;

    /**
     * 验证角色是否有指定权限
     *
     * @param roleIds 角色ID列表
     * @param permissionIds 权限ID列表
     * @return 是否有指定权限
     */
    @Override
    public boolean validateRolesHasPermissions(List<Integer> roleIds, List<Integer> permissionIds) {
        List<Integer> rolePermissionIds = ppPermissionQueryDomain.getPermissionIdsByRoleIds(roleIds);
        return new HashSet<>(rolePermissionIds).containsAll(permissionIds);
    }

    /**
     * 验证角色是否有指定权限
     * @param roleIds 角色ID列表
     * @param permissionId 权限ID
     * @return 是否有指定权限
     */
    @Override
    public boolean validateRolesHasPermission(List<Integer> roleIds, Integer permissionId) {
        return validateRolesHasPermissions(roleIds, List.of(permissionId));
    }
}
