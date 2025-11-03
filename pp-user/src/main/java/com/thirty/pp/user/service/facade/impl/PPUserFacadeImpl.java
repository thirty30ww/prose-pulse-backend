package com.thirty.pp.user.service.facade.impl;

import com.thirty.common.exception.BusinessException;
import com.thirty.pp.user.converter.PPUserConverter;
import com.thirty.pp.user.enums.result.PPUserResultCode;
import com.thirty.pp.user.model.dto.PPAddUserDTO;
import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.model.vo.PPUserVO;
import com.thirty.pp.user.service.domain.user.PPUserOperationDomain;
import com.thirty.pp.user.service.domain.user.PPUserQueryDomain;
import com.thirty.pp.user.service.facade.PPUserFacade;
import com.thirty.user.model.dto.AddUserDTO;
import com.thirty.user.model.vo.UserVO;
import com.thirty.user.service.facade.UserFacade;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PPUserFacadeImpl implements PPUserFacade {
    @Resource
    private UserFacade userFacade;
    @Resource
    private PPUserQueryDomain ppUserQueryDomain;
    @Resource
    private PPUserOperationDomain ppUserOperationDomain;

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户VO
     */
    @Override
    public PPUserVO getUser(Integer userId) {
        UserVO userVO = userFacade.getUser(userId);
        PPUsrDetail detail = ppUserQueryDomain.getPPUsrDetail(userId);

        return PPUserConverter.INSTANCE.toPPUserVO(userVO, detail);
    }

    /**
     * 新增用户
     *
     * @param dto            新增用户DTO
     * @param operatorUserId 操作人用户ID
     */
    @Override
    public void addUser(PPAddUserDTO dto, Integer operatorUserId) {
        // 转换为AddUserDTO
        AddUserDTO addUserDTO = PPUserConverter.INSTANCE.toAddUserDTO(dto);
        Integer userId = userFacade.addUser(operatorUserId, addUserDTO);

        // 校验邮箱是否已存在
        if (ppUserQueryDomain.getPPUsrDetail(dto.getEmail()) != null) {
            throw new BusinessException(PPUserResultCode.EMAIL_IS_EXIST);
        }
        PPUsrDetail detail = PPUserConverter.INSTANCE.toPPUsrDetail(dto, userId);
        ppUserOperationDomain.addUser(detail);
    }
}