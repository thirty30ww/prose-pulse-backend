package com.thirty.pp.user.service.facade.impl;

import com.thirty.pp.user.converter.PPUserConverter;
import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.model.vo.PPUserVO;
import com.thirty.pp.user.service.basic.PPUsrDetailService;
import com.thirty.pp.user.service.facade.PPUserFacade;
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
    private PPUsrDetailService ppUsrDetailService;

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户VO
     */
    @Override
    public PPUserVO getUser(Integer userId) {
        UserVO userVO = userFacade.getUser(userId);
        PPUsrDetail ppUsrDetail = ppUsrDetailService.getById(userId);

        // 如果拓展用户信息不存在，则创建一个新的
        ppUsrDetail = ppUsrDetail != null ? ppUsrDetail : new PPUsrDetail();

        return PPUserConverter.INSTANCE.toPPUserVO(userVO, ppUsrDetail);
    }
}