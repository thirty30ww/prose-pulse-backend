package com.thirty.pp.user.service.domain.impl;

import com.thirty.pp.user.model.entity.PPUsrDetail;
import com.thirty.pp.user.service.basic.PPUsrDetailService;
import com.thirty.pp.user.service.domain.PPUserOperationDomain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PPUserOperationDomainImpl implements PPUserOperationDomain {
    @Resource
    private PPUsrDetailService ppUsrDetailService;

    @Override
    public void addUser(PPUsrDetail detail) {
        ppUsrDetailService.save(detail);
    }
}
