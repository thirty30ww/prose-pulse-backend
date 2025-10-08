package com.thirty.pp.core.aspect;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自动数据源切换切面
 * 根据包路径自动选择对应的数据源
 */
@Aspect
@Component
@Order(2) // 确保在事务之前执行
@Slf4j
public class PPAutoDataSourceAspect {
    @Before("execution(* com.thirty.pp.user.service..*(..))")
    public void setPPUserDataSource() {
        DynamicDataSourceContextHolder.push("pp_user");
    }

    @After("execution(* com.thirty.pp.user.service..*(..))")
    public void clearPPUserDataSource() {
        DynamicDataSourceContextHolder.clear();
    }
}
