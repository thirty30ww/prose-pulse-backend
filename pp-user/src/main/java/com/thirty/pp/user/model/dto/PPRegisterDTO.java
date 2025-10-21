package com.thirty.pp.user.model.dto;

import com.thirty.user.model.dto.RegisterDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PPRegisterDTO extends RegisterDTO {
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
