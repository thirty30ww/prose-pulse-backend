package com.thirty.pp.user.service.facade;

public interface PPAuthFacade {
    /**
     * 发送注册验证码，校验邮箱是否已存在，并把验证码存储到Redis中
     * @param to 接收验证码的邮箱
     */
    void sendRegisterCaptcha(String to);
}
