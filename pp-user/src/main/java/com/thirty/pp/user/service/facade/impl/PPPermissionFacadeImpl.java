package com.thirty.pp.user.service.facade.impl;

import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.vo.PPPermissionVO;
import com.thirty.pp.user.service.domain.permission.PPPermissionOperationDomain;
import com.thirty.pp.user.service.domain.permission.PPPermissionQueryDomain;
import com.thirty.pp.user.service.domain.permission.PPPermissionValidationDomain;
import com.thirty.pp.user.service.facade.PPPermissionFacade;
import com.thirty.user.service.basic.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PPPermissionFacadeImpl implements PPPermissionFacade {
    @Resource
    private PPPermissionValidationDomain ppPermissionValidationDomain;
    @Resource
    private PPPermissionOperationDomain ppPermissionOperationDomain;
    @Resource
    private PPPermissionQueryDomain ppPermissionQueryDomain;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 添加权限
     * @param dto 权限DTO
     * @param userId 用户ID
     * @return 权限ID
     */
    @Override
    public Integer addPermission(PPPermissionDTO dto, Integer userId) {
        return ppPermissionOperationDomain.addPermission(dto);
    }

    /**
     * 修改权限
     * @param dto 权限DTO
     * @param userId 用户ID
     * @return 权限ID
     */
    @Override
    public Integer modifyPermission(PPPermissionDTO dto, Integer userId) {
        return ppPermissionOperationDomain.modifyPermission(dto);
    }

     /**
     * 获取权限树
     * @param userId 用户ID
     * @return 权限树列表
     */
     @Override
     public List<PPPermissionVO> getTree(Integer userId) {
        List<Integer> roleIds = userRoleService.getRoleIdsByUserId(userId);
        return ppPermissionQueryDomain.getTree(roleIds);
    }
}
