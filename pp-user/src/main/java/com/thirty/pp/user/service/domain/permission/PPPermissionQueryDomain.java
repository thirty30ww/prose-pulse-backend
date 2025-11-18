package com.thirty.pp.user.service.domain.permission;

import com.thirty.pp.user.model.vo.PPPermissionVO;
import io.github.thirty30ww.defargs.annotation.Omittable;

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
    List<PPPermissionVO> getTree(List<Integer> roleIds, @Omittable Boolean onlyNeedVerify, @Omittable Integer rootParentId);
}