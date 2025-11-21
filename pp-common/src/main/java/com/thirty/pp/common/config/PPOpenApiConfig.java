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
                .pathsToMatch("/pp/user/**", "/pp/auth/**")  // 匹配的路径
                .build();
    }

    @Bean
    public GroupedOpenApi ppPermissionApi() {
        return GroupedOpenApi.builder()
                .group("前台权限模块")  // 分组名称
                .pathsToMatch("/pp/permission/**")  // 匹配的路径
                .build();
    }
}