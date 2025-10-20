package com.thirty.pp.common.enums.result;

import com.thirty.common.enums.IResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailResultCode implements IResult {
    EMAIL_DETAIL_CANNOT_BE_EMPTY(6001, "邮箱详情不能为空"),
    EMAIL_SEND_FAILED(6002, "邮箱发送失败"),
    ;

    private final Integer code;
    private final String message;
}
