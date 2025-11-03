package com.thirty.pp.user.service.domain.user.impl;

import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.service.basic.PPUsrDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PPUserQueryDomain implements com.thirty.pp.user.service.domain.user.PPUserQueryDomain {
    @Resource
    private PPUsrDetailService ppUsrDetailService;

    /**
     * 根据用户ID查询用户拓展信息
     *
     * @param userId 用户ID
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    @Override
    public PPUsrDetail getPPUsrDetail(Integer userId) {
        PPUsrDetail detail = ppUsrDetailService.getById(userId);
        return detail != null ? detail : new PPUsrDetail();
    }

    /**
     * 根据用户邮箱查询用户拓展信息
     *
     * @param email 用户邮箱
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    @Override
    public PPUsrDetail getPPUsrDetail(String email) {
        return ppUsrDetailService.getByEmail(email);
    }
}
