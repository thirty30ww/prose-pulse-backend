package com.thirty.pp.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PPOpenApiConfig {
    /**
     * 覆盖用户模块API分组
     */
    @Bean
    public GroupedOpenApi ppUserApi() {
        return GroupedOpenApi.builder()
                .group("覆盖用户模块")  // 分组名称
                .pathsToMatch("/pp/user/**")  // 匹配的路径
                .build();
    }

    /**
     * 覆盖权限模块API分组
     */
    @Bean
    public GroupedOpenApi ppAuthApi() {
        return GroupedOpenApi.builder()
                .group("覆盖权限模块")  // 分组名称
                .pathsToMatch("/pp/auth/**")  // 匹配的路径
                .build();
    }
}