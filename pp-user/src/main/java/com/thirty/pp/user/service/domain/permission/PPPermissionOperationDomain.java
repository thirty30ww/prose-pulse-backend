package com.thirty.pp.user.service.domain.permission;

import com.thirty.pp.user.model.dto.PPPermissionDTO;

public interface PPPermissionOperationDomain {
    /**
     * 添加权限
     * @param dto 权限DTO
     * @return 权限ID
     */
    Integer addPermission(PPPermissionDTO dto);

    /**
     * 修改权限
     * @param dto 权限DTO
     * @return 权限ID
     */
    Integer modifyPermission(PPPermissionDTO dto);
}
