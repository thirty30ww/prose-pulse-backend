package com.thirty.pp.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PPPermissionDTO {
    public interface Add {}
    public interface Modify {}

    @NotNull(groups = Modify.class, message = "权限ID不能为空")
    private Integer id;
    @NotBlank(groups = {Add.class, Modify.class}, message = "权限名称不能为空")
    private String name;
    private String path;
    private String component;
    private String permissionCode;
    @NotNull(groups = {Add.class, Modify.class}, message = "父权限ID不能为空")
    private Integer parentId;
    @NotNull(groups = {Add.class, Modify.class}, message = "是否需要验证不能为空")
    private Boolean needVerify;
    @NotNull(groups = {Add.class, Modify.class}, message = "是否有效不能为空")
    private Boolean isValid;
}
