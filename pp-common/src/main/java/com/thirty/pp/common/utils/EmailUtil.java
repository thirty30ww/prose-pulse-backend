package com.thirty.pp.common.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.thirty.common.exception.BusinessException;
import com.thirty.pp.common.enums.result.EmailResultCode;
import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class EmailUtil {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String defaultFromEmail;

    @Value("${spring.mail.nickname}")
    private String defaultFromName;

    /**
     * 简化版发送邮件 - 使用配置的默认发件人
     */
    public void send(String to, String subject, String content) {
        send(to, subject, content, false, null, null, null);
    }

    /**
     * 完整版发送邮件 - 使用配置的默认发件人
     */
    public void send(String to, String subject, String content, Boolean isHtml,
                     String cc, String bcc, List<File> files) {

        if (StringUtils.isAnyBlank(to, subject, content)) {
            throw new BusinessException(EmailResultCode.EMAIL_DETAIL_CANNOT_BE_EMPTY);
        }

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);

            // 使用配置的默认发件人
            messageHelper.setFrom(new InternetAddress(defaultFromName + "<" + defaultFromEmail + ">"));
            messageHelper.setTo(to.split(","));
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);

            // 设置抄送和密送
            if (!StringUtils.isEmpty(cc)) {
                messageHelper.setCc(cc.split(","));
            }
            if (!StringUtils.isEmpty(bcc)) {
                messageHelper.setBcc(bcc.split(","));
            }

            // 添加附件
            if (CollectionUtils.isNotEmpty(files)) {
                for (File file : files) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }

            // 设置发送日期为当前时间
            messageHelper.setSentDate(new Date());
            javaMailSender.send(messageHelper.getMimeMessage());

        } catch (Exception e) {
            throw new BusinessException(EmailResultCode.EMAIL_SEND_FAILED);
        }
    }
}