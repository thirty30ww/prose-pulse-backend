package com.thirty.pp.user.service.facade.impl;

import com.thirty.common.exception.BusinessException;
import com.thirty.pp.user.enums.result.PPUserResultCode;
import com.thirty.pp.user.service.domain.PPAuthDomain;
import com.thirty.pp.user.service.domain.PPUserQueryDomain;
import com.thirty.pp.user.service.facade.PPAuthFacade;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PPAuthFacadeImpl implements PPAuthFacade {
    @Resource
    private PPAuthDomain ppAuthDomain;
    @Resource
    private PPUserQueryDomain ppUserQueryDomain;

    /**
     * 发送注册验证码，校验邮箱是否已存在，并把验证码存储到Redis中
     * @param to 接收验证码的邮箱
     */
    @Override
    public void sendRegisterCaptcha(String to) {
        // 校验邮箱是否已存在
        if (ppUserQueryDomain.getPPUsrDetail(to) != null) {
            throw new BusinessException(PPUserResultCode.EMAIL_IS_EXIST);
        }
        // 发送注册验证码
        ppAuthDomain.sendRegisterCaptcha(to);
    }
}
