package com.thirty.pp.user.service.basic.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thirty.pp.user.mapper.PPUsrPermissionMapper;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import com.thirty.pp.user.service.basic.PPUsrPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【pp_usr_permission(前台权限表)】的数据库操作Service实现
* @createDate 2025-10-25 13:03:48
*/
@Service
public class PPUsrPermissionServiceImpl extends ServiceImpl<PPUsrPermissionMapper, PPUsrPermission>
    implements PPUsrPermissionService{

    /**
     * 添加权限
     * @param permission 权限实体
     * @return 权限ID
     */
    @Override
    public Integer addPermission(PPUsrPermission permission) {
        this.save(permission);
        return permission.getId();
    }

    /**
     * 修改权限
     * @param permission 权限实体
     * @return 权限ID
     */
    @Override
    public Integer modifyPermission(PPUsrPermission permission) {
        this.updateById(permission);
        return permission.getId();
    }

    /**
     * 获取父权限下的所有子权限，按order升序排序
     * @param parentId 父权限ID
     * @return 子权限列表
     */
    @Override
    public List<PPUsrPermission> getByParentId(Integer parentId) {
        QueryWrapper<PPUsrPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId)
                .orderByAsc("order");
        return this.list(queryWrapper);
    }

    /**
     * 获取父权限下的最后一个子权限的order值
     * @param ParentId 父权限ID
     * @return 最后一个子权限的order值
     */
    @Override
    public Integer getLastOrderByParentId(Integer ParentId) {
        QueryWrapper<PPUsrPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("order")
                .eq("parent_id", ParentId)
                .orderByDesc("order")
                .last("limit 1");
        PPUsrPermission permission = this.getOne(queryWrapper);
        return permission.getOrder();
    }

    /**
     * 修改权限的父权限ID
     * @param oldPermission 旧权限实体
     * @param newPermission 新权限实体
     */
    @Override
    public void modifyParentId(PPUsrPermission oldPermission, PPUsrPermission newPermission) {
        // 连接邻居权限
        this.connectNeighborPermissions(oldPermission);
        this.tailInsert(newPermission);
    }

    /**
     * 尾插法添加权限
     * @param permission 权限实体
     */
    @Override
    public void tailInsert(PPUsrPermission permission) {
        Integer lastOrder = this.getLastOrderByParentId(permission.getParentId());
        permission.setOrder(lastOrder + 1);
    }

    /**
     * 连接邻居权限
     * @param permission 权限实体
     */
    @Override
    public void connectNeighborPermissions(PPUsrPermission permission) {
        // 获取和当前权限父权限相同的所有权限
        List<PPUsrPermission> sameParentPermissions = this.getByParentId(permission.getParentId());

        // 获得 order 大于当前权限的所有权限
        List<PPUsrPermission> behindPermissions = PPUsrPermission.getBehindPermissions(sameParentPermissions, permission.getOrder());
        // 后面权限的 order 值减1
        PPUsrPermission.reduceOrder(behindPermissions);
    }
}




