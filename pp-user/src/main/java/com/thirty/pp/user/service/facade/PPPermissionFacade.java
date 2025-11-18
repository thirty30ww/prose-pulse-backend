package com.thirty.pp.user.service.facade;

import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.vo.PPPermissionVO;

import java.util.List;

public interface PPPermissionFacade {
    /**
     * 添加权限
     * @param dto 权限DTO
     * @param userId 用户ID
     * @return 权限ID
     */
    Integer addPermission(PPPermissionDTO dto, Integer userId);

    /**
     * 修改权限
     * @param dto 权限DTO
     * @param userId 用户ID
     * @return 权限ID
     */
    Integer modifyPermission(PPPermissionDTO dto, Integer userId);

     /**
      * 获取权限树
      * @param userId 用户ID
      * @return 权限树列表
      */
    List<PPPermissionVO> getTree(Integer userId);
}
