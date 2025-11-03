package com.thirty.pp.user.service.domain.user;

import com.thirty.pp.user.model.entity.PPUsrDetail;

public interface PPUserQueryDomain {
    /**
     * 根据用户ID查询用户拓展信息
     *
     * @param userId 用户ID
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    PPUsrDetail getPPUsrDetail(Integer userId);

    /**
     * 根据用户邮箱查询用户拓展信息
     *
     * @param email 用户邮箱
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    PPUsrDetail getPPUsrDetail(String email);
}
