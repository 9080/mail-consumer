package com.helon.mail.utils.com.helon.mail.vo;

import java.util.Map;

/**
 * @Author: Helon
 * @Description: 邮件发送数据封装bean
 * @Data: Created in 2018/2/4 21:35
 * @Modified By:
 */
public class MailData {
    //收件人
    private String userId;
    //邮件主题
    private String subject;
    //邮件发送人
    private String from;
    //邮件收件人
    private String to;
    //邮件内容
    private String content;
    //邮件模板名称
    private String templateName;
    //参数集
    private Map<String, Object> params;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
