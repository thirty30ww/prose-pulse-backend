package com.thirty.pp.user.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import io.github.thirty30ww.defargs.annotation.Omittable;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【pp_usr_permission(前台权限表)】的数据库操作Service
* @createDate 2025-10-25 13:03:48
*/
public interface PPUsrPermissionService extends IService<PPUsrPermission> {

    /**
     * 添加权限
     * @param permission 权限实体
     * @return 权限ID
     */
    Integer addPermission(PPUsrPermission permission);

    /**
     * 修改权限
     * @param permission 权限实体
     * @return 权限ID
     */
    Integer modifyPermission(PPUsrPermission permission);

    /**
     * 根据父权限ID获取所有子权限
     * @param parentId 父权限ID
     * @return 子权限列表
     */
    List<PPUsrPermission> getByParentId(Integer parentId);

    /**
     * 获取父权限下的最后一个子权限的order值
     * @param ParentId 父权限ID
     * @return 最后一个子权限的order值
     */
    Integer getLastOrderByParentId(Integer ParentId);

    /**
     * 修改权限的父权限ID
     * @param oldPermission 旧权限实体
     * @param newPermission 新权限实体
     */
    void modifyParentId(PPUsrPermission oldPermission, PPUsrPermission newPermission);

    /**
     * 尾插法添加权限
     * @param permission 权限实体
     */
    void tailInsert(PPUsrPermission permission);

     /**
      * 连接邻居权限
      * @param permission 权限实体
      */
    void connectNeighborPermissions(PPUsrPermission permission);

    /**
     * 获取所有权限，按parent_id升序，order升序排序
     * @param onlyNeedVerify 是否仅包含需要验证的权限，默认值为true
     * @return 所有权限列表
     */
    List<PPUsrPermission> getPermissions(@Omittable Boolean onlyNeedVerify);
}
