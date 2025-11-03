package com.thirty.pp.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限表
 * @TableName pp_usr_role_permission
 */
@TableName(value ="pp_usr_role_permission")
@Data
public class PPUsrRolePermission implements Serializable {
    /**
     * 角色权限ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除 1-已删除，0-未删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 从角色权限列表中提取所有权限ID
     * @param rolePermissions 角色权限列表
     * @return 所有权限ID列表
     */
    public static List<Integer> extractPermissionIds(List<PPUsrRolePermission> rolePermissions) {
        return rolePermissions.stream()
                .map(PPUsrRolePermission::getPermissionId)
                .collect(Collectors.toList());
    }
}