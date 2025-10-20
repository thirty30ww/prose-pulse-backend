package com.thirty.pp.core;

import com.thirty.pp.common.utils.EmailUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PPApplicationTests {

    @Resource
    private EmailUtil emailUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void sendSimpleEmail() {
        emailUtil.send(
                "2834379272@qq.com",      // 收件人
                "测试邮件主题",            // 主题
                "这是一封测试邮件内容"     // 内容
        );
    }
}
