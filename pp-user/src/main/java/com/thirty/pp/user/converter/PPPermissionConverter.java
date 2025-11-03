package com.thirty.pp.user.converter;

import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.entity.PPUsrPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PPPermissionConverter {
    PPPermissionConverter INSTANCE = Mappers.getMapper(PPPermissionConverter.class);

    /**
     * 将PPPermissionDTO转换为PPUsrPermission
     * @param dto 权限DTO
     * @return 转换后的PPUsrPermission
     */
    PPUsrPermission toPPUsrPermission(PPPermissionDTO dto);
}
