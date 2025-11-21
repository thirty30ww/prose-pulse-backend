package com.thirty.pp.user.enums.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.thirty.common.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PPPermissionType implements CodeEnum<Integer> {
    DIRECTORY(1, "目录"),
    PAGE(2, "页面"),
    BUTTON(3, "按钮"),
    ;

    @EnumValue  // MyBatis-Plus会使用这个值存储到数据库
    private final Integer code;

    @JsonValue  // JSON序列化时返回这个值
    private final String type;

    public static PPPermissionType getByCode(Integer code) {
        return CodeEnum.getByCode(PPPermissionType.class, code);
    }
}
