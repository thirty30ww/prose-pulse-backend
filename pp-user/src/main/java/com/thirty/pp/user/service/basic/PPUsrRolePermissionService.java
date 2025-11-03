package com.thirty.pp.user.service.basic;

import com.thirty.pp.user.model.entity.PPUsrRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【pp_usr_role_permission(角色权限表)】的数据库操作Service
* @createDate 2025-11-03 16:57:46
*/
public interface PPUsrRolePermissionService extends IService<PPUsrRolePermission> {

    List<Integer> getByRoleIds(List<Integer> roleId);
}
