package com.thirty.pp.user.converter;

import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.model.vo.PPUserVO;
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
}
