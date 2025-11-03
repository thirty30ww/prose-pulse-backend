package com.thirty.pp.user.model.dto;

import lombok.Data;

@Data
public class PPPermissionDTO {
    private Integer id;
    private String name;
    private String path;
    private String component;
    private String permissionCode;
    private Integer parentId;
    private Boolean needVerify;
    private Boolean isValid;
}
