package com.thirty.pp.user.constant;

/**
 * 验证码相关常量
 */
public class CaptchaConstant {
    
    /**
     * 验证码Redis键前缀
     */
    public static final String CAPTCHA_KEY_PREFIX = "captcha:";
    
    /**
     * 验证码类型 - 注册
     */
    public static final String CAPTCHA_TYPE_REGISTER = "register";
    
    /**
     * 验证码类型 - 登录
     */
    public static final String CAPTCHA_TYPE_LOGIN = "login";
    
    /**
     * 验证码长度
     */
    public static final int CAPTCHA_LENGTH = 6;
    
    private CaptchaConstant() {
        // 私有构造函数，防止实例化
    }
}