package com.thirty.pp.user.service.domain.permission.impl;

import com.thirty.pp.user.converter.PPPermissionConverter;
import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import com.thirty.pp.user.service.basic.PPUsrPermissionService;
import com.thirty.pp.user.service.domain.permission.PPPermissionOperationDomain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class PPPermissionOperationDomainImpl implements PPPermissionOperationDomain {
    @Resource
    private PPUsrPermissionService ppUsrPermissionService;

    /**
     * 添加权限
     * @param dto 权限DTO
     * @return 权限ID
     */
    @Override
    public Integer addPermission(PPPermissionDTO dto) {
        // 转化为实体类
        PPUsrPermission permission = PPPermissionConverter.INSTANCE.toPPUsrPermission(dto);

        // 设置权限的order值
        ppUsrPermissionService.tailInsert(permission);

        return ppUsrPermissionService.addPermission(permission);
    }

    /**
     * 修改权限
     * @param dto 权限DTO
     * @return 权限ID
     */
    @Override
    public Integer modifyPermission(PPPermissionDTO dto) {
        PPUsrPermission permission = PPPermissionConverter.INSTANCE.toPPUsrPermission(dto);
        PPUsrPermission oldPermission = ppUsrPermissionService.getById(permission.getId());

        // 如果修改了父权限ID
        if (!Objects.equals(permission.getParentId(), oldPermission.getParentId()))
            ppUsrPermissionService.modifyParentId(oldPermission, permission);

        // 更新权限
        return ppUsrPermissionService.modifyPermission(permission);
    }
}
