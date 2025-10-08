package com.thirty.pp.user.service.facade;

import com.thirty.pp.user.model.vo.PPUserVO;

public interface PPUserFacade {
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户VO
     */
    PPUserVO getUser(Integer userId);
}
