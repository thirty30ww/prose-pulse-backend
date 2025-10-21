package com.thirty.pp.user.enums.result;

import com.thirty.common.enums.IResult;
import com.thirty.common.enums.result.GlobalResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PPAuthResultCode implements IResult {
    CAPTCHA_ERROR(8001, "验证码错误"),

    SEND_REGISTER_CAPTCHA_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "发送注册验证码成功"),
    REGISTER_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "注册成功"),
    ;

    private final Integer code;
    private final String message;
}
