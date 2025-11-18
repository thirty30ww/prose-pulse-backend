package com.thirty.pp.user.controller;

import com.thirty.common.model.dto.ResultDTO;
import com.thirty.pp.user.enums.result.PPPermissionResultCode;
import com.thirty.pp.user.model.dto.PPPermissionDTO;
import com.thirty.pp.user.model.vo.PPPermissionVO;
import com.thirty.pp.user.service.facade.PPPermissionFacade;
import com.thirty.user.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pp/permission")
public class PPPermissionController {
    @Resource
    private PPPermissionFacade ppPermissionFacade;
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResultDTO<Integer> addPermission(@RequestBody PPPermissionDTO dto, @RequestHeader("Authorization") String authHeader) {
        Integer userId = jwtUtil.getUserIdFromAuthHeader(authHeader);
        return ResultDTO.of(PPPermissionResultCode.ADD_PERMISSION_SUCCESS, ppPermissionFacade.addPermission(dto, userId));
    }

    @PostMapping("/modify")
    public ResultDTO<Integer> modifyPermission(@RequestBody PPPermissionDTO dto, @RequestHeader("Authorization") String authHeader) {
        Integer userId = jwtUtil.getUserIdFromAuthHeader(authHeader);
        return ResultDTO.of(PPPermissionResultCode.MODIFY_PERMISSION_SUCCESS, ppPermissionFacade.modifyPermission(dto, userId));
    }

    @GetMapping("/get")
    public ResultDTO<List<PPPermissionVO>> getPermission(@RequestHeader("Authorization") String authHeader) {
        Integer userId = jwtUtil.getUserIdFromAuthHeader(authHeader);
        return ResultDTO.of(PPPermissionResultCode.GET_PERMISSION_SUCCESS, ppPermissionFacade.getTree(userId));
    }
}
