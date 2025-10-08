package com.thirty.pp.user.model.vo;

import com.thirty.user.model.vo.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PPUserVO extends UserVO {
    private String email;
}
