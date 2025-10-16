package com.thirty.pp.user.service.facade;

import com.thirty.pp.user.model.dto.PPAddUserDTO;
import com.thirty.pp.user.model.vo.PPUserVO;

public interface PPUserFacade {
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户VO
     */
    PPUserVO getUser(Integer userId);

    /**
     * 新增用户
     * @param dto 新增用户DTO
     * @param operatorUserId 操作人用户ID
     */
    void addUser(PPAddUserDTO dto, Integer operatorUserId);
}
