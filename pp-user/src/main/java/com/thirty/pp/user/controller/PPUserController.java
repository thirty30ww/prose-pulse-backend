package com.thirty.pp.user.controller;

import com.thirty.common.annotation.OperateLog;
import com.thirty.common.enums.model.OperationType;
import com.thirty.common.model.dto.ResultDTO;
import com.thirty.pp.user.model.vo.PPUserVO;
import com.thirty.pp.user.service.facade.PPUserFacade;
import com.thirty.user.enums.result.UserResultCode;
import com.thirty.user.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pp/user")
@Order(Ordered.HIGHEST_PRECEDENCE) // 添加优先级
public class PPUserController {
    @Resource
    private PPUserFacade ppUserFacade;
    @Resource
    private JwtUtil jwtUtil;

    @GetMapping({"/get"})
    @OperateLog(
            type = OperationType.SELECT,
            description = "获取用户信息"
    )
    public ResultDTO<PPUserVO> getUser(@RequestHeader("Authorization") String authHeader) {
        Integer userId = this.jwtUtil.getUserIdFromAuthHeader(authHeader);
        return ResultDTO.of(UserResultCode.USER_INFO_GET_SUCCESS, ppUserFacade.getUser(userId));
    }
}
