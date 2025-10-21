package com.thirty.pp.user.controller;

import com.thirty.common.annotation.OperateLog;
import com.thirty.common.annotation.OperateModule;
import com.thirty.common.enums.model.OperationType;
import com.thirty.common.model.dto.ResultDTO;
import com.thirty.pp.user.enums.result.PPAuthResultCode;
import com.thirty.pp.user.model.dto.PPRegisterDTO;
import com.thirty.pp.user.service.facade.PPAuthFacade;
import com.thirty.user.annotation.LoginLog;
import com.thirty.user.enums.model.LoginType;
import com.thirty.user.model.vo.JwtVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pp/auth")
@OperateModule("权限管理")
public class PPAuthController {
    @Resource
    private PPAuthFacade ppAuthFacade;

    /**
     * 发送注册验证码，校验邮箱是否已存在，并把验证码存储到Redis中
     * @param email 接收验证码的邮箱
     */
    @GetMapping("/captcha/register")
    @OperateLog(
            type = OperationType.SEND,
            description = "发送注册验证码"
    )
    public ResultDTO<Void> sendRegisterCaptcha(@RequestParam String email) {
        ppAuthFacade.sendRegisterCaptcha(email);
        return ResultDTO.of(PPAuthResultCode.SEND_REGISTER_CAPTCHA_SUCCESS);
    }

    /**
     * 注册用户，校验验证码是否正确，若正确则注册用户并返回JwtVO
     * @param dto 注册用户DTO
     * @return JwtVO
     */
    @PostMapping("/register")
    @LoginLog(type = LoginType.REGISTER)
    public ResultDTO<JwtVO> register(@RequestBody PPRegisterDTO dto) {
        JwtVO jwtVO = ppAuthFacade.register(dto);
        return ResultDTO.of(PPAuthResultCode.REGISTER_SUCCESS, jwtVO);
    }
}
