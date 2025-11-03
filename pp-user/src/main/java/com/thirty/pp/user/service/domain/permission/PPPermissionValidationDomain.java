package com.thirty.pp.user.service.domain.permission;

import java.util.List;

public interface PPPermissionValidationDomain {
    /**
     * 验证角色是否有指定权限
     * @param roleIds 角色ID列表
     * @param permissionIds 权限ID列表
     * @return 是否有指定权限
     */
    boolean validateRoleHasPermissions(List<Integer> roleIds, List<Integer> permissionIds);

     /**
      * 验证角色是否有指定权限
      * @param roleIds 角色ID列表
      * @param permissionId 权限ID
      * @return 是否有指定权限
      */
     boolean validateRoleHasPermission(List<Integer> roleIds, Integer permissionId);
}
