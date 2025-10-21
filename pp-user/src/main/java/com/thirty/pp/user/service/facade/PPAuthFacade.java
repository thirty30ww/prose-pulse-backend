package com.thirty.pp.user.service.facade;

import com.thirty.pp.user.model.dto.PPRegisterDTO;
import com.thirty.user.model.vo.JwtVO;

public interface PPAuthFacade {
    /**
     * 发送注册验证码，校验邮箱是否已存在，并把验证码存储到Redis中
     * @param to 接收验证码的邮箱
     */
    void sendRegisterCaptcha(String to);

    /**
     * 注册用户，校验验证码是否正确，若正确则注册用户并返回JwtVO
     * @param dto 注册用户DTO
     * @return JwtVO
     */
    JwtVO register(PPRegisterDTO dto);
}
