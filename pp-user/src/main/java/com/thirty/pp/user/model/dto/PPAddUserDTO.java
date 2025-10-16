package com.thirty.pp.user.model.dto;

import com.thirty.user.model.dto.AddUserDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PPAddUserDTO extends AddUserDTO {
    @NotBlank(message = "邮箱不能为空")
    private String email;
}