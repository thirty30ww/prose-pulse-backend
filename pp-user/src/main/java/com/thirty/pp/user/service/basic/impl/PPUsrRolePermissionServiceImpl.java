package com.thirty.pp.user.service.basic.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thirty.pp.user.mapper.PPUsrRolePermissionMapper;
import com.thirty.pp.user.model.entity.PPUsrRolePermission;
import com.thirty.pp.user.service.basic.PPUsrRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【pp_usr_role_permission(角色权限表)】的数据库操作Service实现
* @createDate 2025-11-03 16:57:46
*/
@Service
public class PPUsrRolePermissionServiceImpl extends ServiceImpl<PPUsrRolePermissionMapper, PPUsrRolePermission>
    implements PPUsrRolePermissionService{

    /**
     * 根据角色ID获取权限ID列表
     * @param roleIds 角色ID列表
     * @return 权限ID列表
     */
    @Override
    public List<Integer> getByRoleIds(List<Integer> roleIds) {
        LambdaQueryWrapper<PPUsrRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(PPUsrRolePermission::getPermissionId)
                .in(PPUsrRolePermission::getRoleId, roleIds);
        List<PPUsrRolePermission> RolePermissions = this.list(queryWrapper);
        return PPUsrRolePermission.extractPermissionIds(RolePermissions);
    }
}




