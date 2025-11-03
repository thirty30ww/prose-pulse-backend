package com.thirty.pp.user.service.domain.auth;

public interface PPAuthDomain {
    /**
     * 发送注册验证码，并把验证码存储到Redis中
     * @param to 接收验证码的邮箱
     */
    void sendRegisterCaptcha(String to);
}
