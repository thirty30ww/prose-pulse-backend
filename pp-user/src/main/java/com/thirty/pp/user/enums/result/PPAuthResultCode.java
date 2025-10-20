package com.thirty.pp.user.enums.result;

import com.thirty.common.enums.IResult;
import com.thirty.common.enums.result.GlobalResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PPAuthResultCode implements IResult {
    SEND_REGISTER_CAPTCHA_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "发送注册验证码成功"),
    ;

    private final Integer code;
    private final String message;
}
