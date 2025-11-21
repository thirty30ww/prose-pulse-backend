package com.thirty.pp.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.thirty.pp.user.enums.model.PPPermissionType;
import io.github.thirty30ww.defargs.annotation.DefaultValue;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台权限表
 * @TableName pp_usr_permission
 */
@TableName(value ="pp_usr_permission")
@Data
public class PPUsrPermission implements Serializable {
    /**
     * 权限id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 页面网页路由路径
     */
    private String path;

    /**
     * 页面，布局的组件位置
     */
    private String component;

    /**
     * 按钮权限码
     */
    private String permissionCode;

    /**
     * 权限类型 1-目录，2-菜单，3-按钮
     */
    private PPPermissionType type;

    /**
     * 父权限ID(根权限的parent_id为0)
     */
    private Integer parentId;

    /**
     * 权限排序
     */
    @TableField(value = "`order`")
    private Integer order;

    /**
     * 是否需要校验 0-不需要(所有用户可用)，1-需要(需登录校验)
     */
    private Boolean needVerify;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否有效 1-有效，0-无效(维护或者零时禁用)
     */
    private Boolean isValid;

    /**
     * 是否已删除 1-已删，0-未删
     */
    @TableLogic
    private Boolean isDelete;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 从权限列表中提取权限ID列表
     * @param permissions 权限列表
     * @return 权限ID列表
     */
    public static List<Integer> extractIds(List<PPUsrPermission> permissions) {
        return permissions.stream().map(PPUsrPermission::getId).collect(Collectors.toList());
    }

    /**
     * 获取父权限下的所有子权限，按order升序排序
     * @param sameParentPermissions 父权限下的所有子权限
     * @param order 权限ID
     * @return 子权限列表
     */
    public static List<PPUsrPermission> getBehindPermissions(List<PPUsrPermission> sameParentPermissions, Integer order) {
        return sameParentPermissions.stream()
                .filter(permission -> permission.getOrder() > order)
                .collect(Collectors.toList());
    }

    /**
     * 减少目标权限的order值
     * @param targetPermissions 目标权限列表
     * @param reduceValue 减少值
     */
    public static void reduceOrder(List<PPUsrPermission> targetPermissions, @DefaultValue("1") Integer reduceValue) {
        targetPermissions.forEach(permission -> permission.setOrder(permission.getOrder() - reduceValue));
    }
}