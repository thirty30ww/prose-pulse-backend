package com.thirty.pp.user.enums.result;

import com.thirty.common.enums.IResult;
import com.thirty.common.enums.result.GlobalResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PPPermissionResultCode implements IResult {
    ADD_PERMISSION_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "添加权限成功"),
    MODIFY_PERMISSION_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "修改权限成功"),
    GET_PERMISSION_SUCCESS(GlobalResultCode.SUCCESS.getCode(), "获取权限成功"),
    ;
    private final Integer code;
    private final String message;
}
