package com.thirty.pp.user.model.vo;

import com.thirty.pp.user.model.entity.PPUsrPermission;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PPPermissionVO {
    PPUsrPermission node;
    Boolean hasPermission = false;
    List<PPPermissionVO> children = new ArrayList<>();

    /**
     * 为每个权限VO添加是否有该权限的标志，如果权限ID列表包含该权限ID，则设置为true，否则为false
     * @param permissionVOS 权限VO列表
     * @param permissionIds 权限ID列表
     */
    public static void setHasPermission(List<PPPermissionVO> permissionVOS, List<Integer> permissionIds) {
        permissionVOS.forEach(permissionVO ->
                permissionVO.setHasPermission(permissionIds.contains(permissionVO.getNode().getId()))
        );
    }
}