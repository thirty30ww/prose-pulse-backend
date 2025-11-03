package com.thirty.pp.user.service.facade.impl;

import com.thirty.common.exception.BusinessException;
import com.thirty.pp.user.constant.CaptchaConstant;
import com.thirty.pp.user.converter.PPUserConverter;
import com.thirty.pp.user.enums.result.PPAuthResultCode;
import com.thirty.pp.user.enums.result.PPUserResultCode;
import com.thirty.pp.user.model.dto.PPRegisterDTO;
import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.service.basic.CaptchaService;
import com.thirty.pp.user.service.domain.auth.PPAuthDomain;
import com.thirty.pp.user.service.domain.user.PPUserOperationDomain;
import com.thirty.pp.user.service.domain.user.PPUserQueryDomain;
import com.thirty.pp.user.service.facade.PPAuthFacade;
import com.thirty.user.model.vo.JwtVO;
import com.thirty.user.service.facade.AuthFacade;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PPAuthFacadeImpl implements PPAuthFacade {
    @Resource
    private PPAuthDomain ppAuthDomain;
    @Resource
    private PPUserQueryDomain ppUserQueryDomain;
    @Resource
    private PPUserOperationDomain ppUserOperationDomain;

    @Resource
    private AuthFacade authFacade;

    @Resource
    private CaptchaService captchaService;

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

    /**
     * 注册用户，校验验证码是否正确，若正确则注册用户并返回JwtVO
     * @param dto 注册用户DTO
     * @return JwtVO
     */
    @Override
    public JwtVO register(PPRegisterDTO dto) {
        String email = dto.getEmail();
        String captcha = dto.getCaptcha();

        // 校验验证码
        if(!captchaService.verifyCaptcha(email, captcha, CaptchaConstant.CAPTCHA_TYPE_REGISTER)) {
            throw new BusinessException(PPAuthResultCode.CAPTCHA_ERROR);
        }
        // 验证完后，删除验证码
        captchaService.deleteCaptcha(email, CaptchaConstant.CAPTCHA_TYPE_REGISTER);

        // 注册用户
        JwtVO response = authFacade.register(dto);

        // 新增用户详情
        Integer userId = response.getUserId();
        PPUsrDetail detail = PPUserConverter.INSTANCE.toPPUsrDetail(dto, userId);
        ppUserOperationDomain.addUser(detail);

        return response;
    }
}
