package com.thirty.pp.user.service.domain.permission.impl;

import com.thirty.pp.user.model.entity.PPUsrPermission;
import com.thirty.pp.user.service.basic.PPUsrPermissionService;
import com.thirty.pp.user.service.basic.PPUsrRolePermissionService;
import com.thirty.pp.user.service.domain.permission.PPPermissionQueryDomain;
import com.thirty.user.constant.RoleConstant;
import com.thirty.user.service.basic.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PPPermissionQueryDomainImpl implements PPPermissionQueryDomain {
    @Resource
    private PPUsrRolePermissionService ppUsrRolePermissionService;
    @Resource
    private PPUsrPermissionService ppUsrPermissionService;
    @Resource
    private RoleService roleService;

    /**
     * 根据角色ID列表获取所有权限ID
     * <p>
     * 包括当前角色的所有子角色的权限
     * <p>
     * 如果角色的level为0，则拥有所有权限
     *
     * @param roleIds 角色ID列表
     * @return 所有权限ID列表
     */
    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        // 如果角色中包含最高级角色，则返回所有权限ID
        Integer highestLevel = roleService.getHighestLevel(roleIds);
        if (Objects.equals(highestLevel, RoleConstant.ROLE_HIGHEST_LEVEL)) {
            List<PPUsrPermission> permissions = ppUsrPermissionService.list();
            return PPUsrPermission.extractIds(permissions);
        }
        // 否则，返回当前角色ID和所有子角色ID的权限ID
        List<Integer> descendantRoleIds = roleService.getDescendantRoleIds(roleIds);
        Set<Integer> allRoleIds = new HashSet<>(roleIds);
        allRoleIds.addAll(descendantRoleIds);  // 合并当前角色ID和所有子角色ID并去重
        return ppUsrRolePermissionService.getPermissionIdsByRoleIds(new ArrayList<>(allRoleIds));
    }


}