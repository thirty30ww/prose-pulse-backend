package com.thirty.pp.user.service.basic.impl;

import com.thirty.pp.common.utils.EmailUtil;
import com.thirty.pp.user.constant.CaptchaConstant;
import com.thirty.pp.user.service.basic.CaptchaService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.Random;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Resource
    private EmailUtil emailUtil;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 项目标题
     */
    @Value("${project.info.title}")
    private String projectTitle;


    /**
     * 发送注册验证码
     * @param to 接收验证码的邮箱
     * @param captcha 验证码内容
     * @param expireMinutes 过期时间（分钟）
     */
    @Override
    public void sendRegisterCaptcha(String to, String captcha, int expireMinutes) {
        emailUtil.send(
                to,
                projectTitle + "注册验证码",
                String.format("【%s】注册验证码：%s，%d分钟内有效。请勿泄露给他人。", projectTitle, captcha, expireMinutes)
        );
    }

    /**
     * 存储验证码到Redis
     * @param email 邮箱地址
     * @param captcha 验证码
     * @param type 验证码类型（如：register、login）
     * @param expireMinutes 过期时间（分钟）
     */
    @Override
    public void storeCaptcha(String email, String captcha, String type, int expireMinutes) {
        String key = CaptchaConstant.CAPTCHA_KEY_PREFIX + type + ":" + email;
        redisTemplate.opsForValue().set(key, captcha, expireMinutes, TimeUnit.MINUTES);
    }

    /**
     * 验证验证码
     * @param email 邮箱地址
     * @param captcha 用户输入的验证码
     * @param type 验证码类型（如：register、login）
     * @return 验证是否成功
     */
    @Override
    public boolean verifyCaptcha(String email, String captcha, String type) {
        // 验证邮箱和验证码是否为空
        if (!StringUtils.isNotBlank(email) || !StringUtils.isNotBlank(captcha) || !StringUtils.isNotBlank(type)) {
            return false;
        }

        // 从Redis获取存储的验证码
        String key = CaptchaConstant.CAPTCHA_KEY_PREFIX + type + ":" + email;
        Object storedCaptcha = redisTemplate.opsForValue().get(key);
        
        if (storedCaptcha == null) {
            return false;
        }
        
        return captcha.equals(storedCaptcha.toString());
    }

    /**
     * 删除验证码
     * @param email 邮箱地址
     * @param type 验证码类型（如：register、login）
     */
    @Override
    public void deleteCaptcha(String email, String type) {
        String key = CaptchaConstant.CAPTCHA_KEY_PREFIX + type + ":" + email;
        redisTemplate.delete(key);
    }

    /**
     * 生成验证码
     * @return 生成的验证码字符串
     */
    @Override
    public String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        
        for (int i = 0; i < CaptchaConstant.CAPTCHA_LENGTH; i++) {
            captcha.append(random.nextInt(10));
        }
        
        return captcha.toString();
    }
}
