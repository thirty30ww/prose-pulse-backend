package com.thirty.pp.user.model.vo;

import com.thirty.pp.user.model.entity.PPUsrPermission;
import lombok.Data;

@Data
public class PPPermissionVO {
    PPUsrPermission node;
    Boolean hasPermission;
    PPPermissionVO child;
}