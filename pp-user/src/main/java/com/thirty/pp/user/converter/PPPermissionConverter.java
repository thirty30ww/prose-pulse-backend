package com.thirty.pp.user.converter;

import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import com.thirty.pp.user.model.vo.PPPermissionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PPPermissionConverter {
    PPPermissionConverter INSTANCE = Mappers.getMapper(PPPermissionConverter.class);

    /**
     * 将PPPermissionDTO转换为PPUsrPermission
     * @param dto 权限DTO
     * @return 转换后的PPUsrPermission
     */
    PPUsrPermission toPPUsrPermission(PPPermissionDTO dto);

    /**
     * 将PPUsrPermission转换为PPPermissionVO
     * @param permission 权限实体
     * @return 转换后的PPPermissionVO
     */
    PPPermissionVO toPPPermissionVO(PPUsrPermission permission);

     /**
     * 将PPUsrPermission列表转换为PPPermissionVO列表
     * @param permissions 权限实体列表
     * @return 转换后的PPPermissionVO列表
     */
    List<PPPermissionVO> toPermissionVOS(List<PPUsrPermission> permissions);
}
