package com.thirty.pp.user.enums.result;

import com.thirty.common.enums.IResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PPUserResultCode implements IResult {

    EMAIL_IS_EXIST(6001, "邮箱已存在"),
    ;

    private final Integer code;
    private final String message;
}
