package com.thirty.pp.user.converter;

import com.thirty.pp.user.model.dto.PPAddUserDTO;
import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.model.vo.PPUserVO;
import com.thirty.user.model.dto.AddUserDTO;
import com.thirty.user.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PPUserConverter {
    PPUserConverter INSTANCE = Mappers.getMapper(PPUserConverter.class);

    /**
     * 将UserVO和PPUsrDetail转换为PPUserVO
     * @param userVO 用户VO
     * @param ppUsrDetail 拓展用户信息实体
     * @return 转换后的PPUserVO
     */
    @Mapping(source = "userVO.id", target = "id")
    @Mapping(source = "userVO.createTime", target = "createTime")
    @Mapping(source = "userVO.updateTime", target = "updateTime")
    PPUserVO toPPUserVO(UserVO userVO, PPUsrDetail ppUsrDetail);

    /**
     * 将PPAddUserDTO转换为PPUsrDetail
     * @param dto 新增用户DTO
     * @param id 用户ID
     * @return 转换后的PPUsrDetail
     */
    @Mapping(source = "dto.email", target = "email")
    PPUsrDetail toPPUsrDetail(PPAddUserDTO dto, Integer id);

    /**
     * 将PPAddUserDTO转换为AddUserDTO
     * @param dto 新增用户DTO
     * @return 转换后的AddUserDTO
     */
    AddUserDTO toAddUserDTO(PPAddUserDTO dto);
}

