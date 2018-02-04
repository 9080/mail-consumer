package com.helon.mail.config.database.com.helon.mail.helper;

import com.helon.mail.constant.Const;
import com.helon.mail.utils.com.helon.mail.vo.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * @Author: Helon
 * @Description: 邮件发送模板
 * @Data: Created in 2018/2/4 21:50
 * @Modified By:
 */
@Component
public class GeneratorMailTemplateHelper {
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * @Author: Helon
     * @Description: 根据发送数据生成模板内容
     * @param data
     * @Data: 2018/2/4 22:35
     * @Modified By:
     */
    public void generatorAndSend(MailData data) throws MessagingException{
        Context context = new Context();
        context.setLocale(Locale.CHINA);
        context.setVariables(data.getParams());
        String templateLocation = data.getTemplateName();
        String content = templateEngine.process(templateLocation, context);
        data.setContent(content);
        //调用发送短信接口
        sendMail(data);
    }

    /**
     * @Author: Helon
     * @Description: 发送邮件
     * @param data
     * @Data: 2018/2/4 22:11
     * @Modified By:
     */
    private void sendMail(MailData data) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, Const.CHARSET_UTF8);
        mimeMessageHelper.setFrom(data.getFrom());
        mimeMessageHelper.setTo(data.getTo());
        mimeMessageHelper.setSubject(data.getSubject());
        mimeMessageHelper.setText(data.getContent(), true);
        mailSender.send(mimeMessage);

    }

}
