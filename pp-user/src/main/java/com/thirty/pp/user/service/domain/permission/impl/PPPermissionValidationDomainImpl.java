package com.thirty.pp.user.service.domain.permission.impl;

import com.thirty.pp.user.service.basic.PPUsrRolePermissionService;
import com.thirty.pp.user.service.domain.permission.PPPermissionValidationDomain;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.List;

public class PPPermissionValidationDomainImpl  implements PPPermissionValidationDomain {

    @Resource
    private PPUsrRolePermissionService usrRolePermissionService;

    /**
     * 验证角色是否有指定权限
     * @param roleIds 角色ID列表
     * @param permissionIds 权限ID列表
     * @return 是否有指定权限
     */
    @Override
    public boolean validateRoleHasPermissions(List<Integer> roleIds, List<Integer> permissionIds) {
        // 获取角色ID列表中所有角色的权限ID列表
        List<Integer> rolePermissionIds = usrRolePermissionService.getByRoleIds(roleIds);
        return new HashSet<>(rolePermissionIds).containsAll(permissionIds);
    }

    /**
     * 验证角色是否有指定权限
     * @param roleIds 角色ID列表
     * @param permissionId 权限ID
     * @return 是否有指定权限
     */
    @Override
    public boolean validateRoleHasPermission(List<Integer> roleIds, Integer permissionId) {
        return validateRoleHasPermissions(roleIds, List.of(permissionId));
    }
}
