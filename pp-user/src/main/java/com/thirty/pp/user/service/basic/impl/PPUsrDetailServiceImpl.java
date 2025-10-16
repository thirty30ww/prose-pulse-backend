package com.thirty.pp.user.service.basic.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thirty.pp.user.mapper.PPUsrDetailMapper;
import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.service.basic.PPUsrDetailService;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【pp_usr_detail(拓展用户信息表)】的数据库操作Service实现
* @createDate 2025-09-30 14:56:37
*/
@Service
public class PPUsrDetailServiceImpl extends ServiceImpl<PPUsrDetailMapper, PPUsrDetail>
    implements PPUsrDetailService{
    /**
     * 根据用户邮箱查询用户拓展信息
     *
     * @param email 用户邮箱
     * @return 用户拓展信息（如果不存在则返回空对象）
     */
    @Override
    public PPUsrDetail getByEmail(String email) {
        return baseMapper.selectOne(new LambdaQueryWrapper<PPUsrDetail>().eq(PPUsrDetail::getEmail, email));
    }
}




