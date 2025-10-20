package com.thirty.pp.user.controller;

import com.thirty.common.model.dto.ResultDTO;
import com.thirty.pp.user.enums.result.PPAuthResultCode;
import com.thirty.pp.user.service.facade.PPAuthFacade;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pp/auth")
public class PPAuthController {
    @Resource
    private PPAuthFacade ppAuthFacade;

    /**
     * 发送注册验证码，校验邮箱是否已存在，并把验证码存储到Redis中
     * @param email 接收验证码的邮箱
     */
    @GetMapping("/captcha/register")
    public ResultDTO<Void> sendRegisterCaptcha(@RequestParam String email) {
        ppAuthFacade.sendRegisterCaptcha(email);
        return ResultDTO.of(PPAuthResultCode.SEND_REGISTER_CAPTCHA_SUCCESS);
    }
}
