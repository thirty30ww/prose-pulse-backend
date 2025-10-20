package com.thirty.pp.user.service.basic;

public interface CaptchaService {
    /**
     * 发送注册验证码
     * @param to 接收验证码的邮箱
     * @param captcha 验证码内容
     * @param expireMinutes 过期时间（分钟）
     */
    void sendRegisterCaptcha(String to, String captcha, int expireMinutes);

    /**
     * 存储验证码到Redis
     * @param email 邮箱地址
     * @param captcha 验证码
     * @param type 验证码类型（如：register、login）
     * @param expireMinutes 过期时间（分钟）
     */
    void storeCaptcha(String email, String captcha, String type, int expireMinutes);

    /**
     * 验证验证码
     * @param email 邮箱地址
     * @param captcha 用户输入的验证码
     * @param type 验证码类型（如：register、login）
     * @return 验证是否成功
     */
    boolean verifyCaptcha(String email, String captcha, String type);

    /**
     * 删除验证码
     * @param email 邮箱地址
     * @param type 验证码类型（如：register、login）
     */
    void deleteCaptcha(String email, String type);

    /**
     * 生成验证码
     * @return 生成的验证码字符串
     */
    String generateCaptcha();
}
