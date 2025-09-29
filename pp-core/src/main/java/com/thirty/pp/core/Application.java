package com.thirty.pp.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.thirty"  // 扫描所有 thirty 包，包括原项目和你的二开代码
        },
        exclude = {
                // 继承原项目的排除配置
                org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration.class,
                org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration.class
        }
)
@MapperScan("com.thirty.**.mapper") // 使用通配符扫描所有模块的 mapper
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
