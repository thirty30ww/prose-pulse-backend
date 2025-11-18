package com.thirty.pp.user.service.domain.permission.impl;

import com.thirty.common.utils.TreeBuilder;
import com.thirty.pp.user.converter.PPPermissionConverter;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import com.thirty.pp.user.model.vo.PPPermissionVO;
import com.thirty.pp.user.service.basic.PPUsrPermissionService;
import com.thirty.pp.user.service.basic.PPUsrRolePermissionService;
import com.thirty.pp.user.service.domain.permission.PPPermissionQueryDomain;
import com.thirty.user.constant.RoleConstant;
import com.thirty.user.service.basic.RoleService;
import io.github.thirty30ww.defargs.annotation.DefaultValue;
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
        // 否则，返回当前角色ID列表的权限ID
        return ppUsrRolePermissionService.getPermissionIdsByRoleIds(new ArrayList<>(roleIds));
    }

    /**
     * 获取权限树
     * <p>
     * 权限树是一个递归结构，每个节点包含权限实体和是否有该权限的标志
     * <p>
     * 权限树的根节点默认父ID为0
     *
     * @param onlyNeedVerify 是否仅包含需要验证的权限，默认值为true
     * @param rootParentId   根节点父ID，默认值为0
     * @param roleIds        角色ID列表
     * @return 权限树列表
     */
    @Override
    public List<PPPermissionVO> getTree(
            List<Integer> roleIds,
            @DefaultValue("true") Boolean onlyNeedVerify,
            @DefaultValue("0") Integer rootParentId
    ) {
        // 获取所有权限实体和转换为VO
        List<PPUsrPermission> permissions = ppUsrPermissionService.getPermissions(onlyNeedVerify);
        List<PPPermissionVO> permissionVOS = PPPermissionConverter.INSTANCE.toPermissionVOS(permissions);

        // 为每个权限VO添加是否有该权限的标志
        List<Integer> permissionIds = getPermissionIdsByRoleIds(roleIds);
        PPPermissionVO.setHasPermission(permissionVOS, permissionIds);

        // 构建成权限树
        TreeBuilder<PPPermissionVO, Integer> treeBuilder = new TreeBuilder<>();
        return treeBuilder.buildTree(
                permissionVOS,
                permissionVO -> permissionVO.getNode().getId(),
                permissionVO -> permissionVO.getNode().getParentId(),
                PPPermissionVO::getChild,
                rootParentId
        );
    }
}