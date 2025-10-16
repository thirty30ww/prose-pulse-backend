package com.thirty.pp.user.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thirty.pp.user.model.entity.PPUsrDetail;

/**
* @author Lenovo
* @description 针对表【pp_usr_detail(拓展用户信息表)】的数据库操作Service
* @createDate 2025-09-30 14:56:37
*/
public interface PPUsrDetailService extends IService<PPUsrDetail> {
    /**
     * 根据用户邮箱查询用户拓展信息
     *
     * @param email 用户邮箱
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    PPUsrDetail getByEmail(String email);
}
