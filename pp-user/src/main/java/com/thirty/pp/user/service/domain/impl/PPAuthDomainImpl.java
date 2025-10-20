package com.thirty.pp.user.service.domain.impl;

import com.thirty.pp.user.constant.CaptchaConstant;
import com.thirty.pp.user.service.basic.CaptchaService;
import com.thirty.pp.user.service.domain.PPAuthDomain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PPAuthDomainImpl implements PPAuthDomain {

    @Resource
    private CaptchaService captchaService;


    public static final int expireMinutes = 5;

    /**
     * 发送注册验证码，并把验证码存储到Redis中
     * @param to 接收验证码的邮箱
     */
    @Override
    public void sendRegisterCaptcha(String to) {
        // 生成验证码
        String captcha = captchaService.generateCaptcha();
        // 发送验证码
        captchaService.sendRegisterCaptcha(to, captcha, expireMinutes);
        // 存储验证码到Redis
        captchaService.storeCaptcha(to, captcha, CaptchaConstant.CAPTCHA_TYPE_REGISTER, expireMinutes);
    }
}
