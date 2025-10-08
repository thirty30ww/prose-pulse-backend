package com.thirty.pp.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * PP项目启动类
 */
@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration.class,
                org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration.class
        }
)
@ComponentScan(
        basePackages = {"com.thirty"},
        excludeFilters = {
                // 排除 auth-matrix 的启动类，避免其 @MapperScan 被处理
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = com.thirty.core.Application.class)
        }
)
@MapperScan("com.thirty.**.mapper")
public class PPApplication {
    public static void main(String[] args) {
        SpringApplication.run(PPApplication.class, args);
    }
}