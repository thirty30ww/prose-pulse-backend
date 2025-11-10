package com.thirty.pp.user.service.facade.impl;

import com.thirty.pp.user.service.domain.permission.PPPermissionOperationDomain;
import com.thirty.pp.user.service.domain.permission.PPPermissionValidationDomain;
import com.thirty.user.service.basic.UserService;
import jakarta.annotation.Resource;

public class PPPermissionFacadeImpl {
    @Resource
    private PPPermissionValidationDomain ppPermissionValidationDomain;
    @Resource
    private PPPermissionOperationDomain ppPermissionOperationDomain;
    @Resource
    private UserService userService;
}
