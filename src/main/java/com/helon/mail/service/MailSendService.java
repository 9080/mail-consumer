package com.helon.mail.service;

import com.helon.mail.config.database.com.helon.mail.helper.GeneratorMailTemplateHelper;
import com.helon.mail.entity.MailSend;
import com.helon.mail.enumeration.MailStatus;
import com.helon.mail.enumeration.RedisPriorityQueue;
import com.helon.mail.mapper.MailSend1Mapper;
import com.helon.mail.mapper.MailSend2Mapper;
import com.helon.mail.utils.FastJsonConvertUtil;
import com.helon.mail.utils.com.helon.mail.vo.MailData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Helon
 * @Description: 邮件发送service
 * @Data: Created in 2018/2/4 11:15
 * @Modified By:
 */
@Service
public class MailSendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
    @Resource
    private MailSend1Mapper mailSend1Mapper;
    @Resource
    private MailSend2Mapper mailSend2Mapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private GeneratorMailTemplateHelper generatorMailTemplateHelper;

    public void sendMessageOrder(MailSend ms) {
        try {
            //1、准备数据
            Map<String, Object> params = new HashMap<>();
            params.put("userName", ms.getSendUserId());
            params.put("createDate", DateFormatUtils.format(ms.getUpdateTime(), "yyyy年MM月dd日"));
            MailData data = new MailData();
            data.setTemplateName("SHEET");
            data.setUserId(ms.getSendUserId());
            data.setFrom("18600210664@163.com");
            data.setTo(ms.getSendTo());
            data.setSubject("helon测试");
            data.setParams(params);
            //2、模板渲染 进行发送
            generatorMailTemplateHelper.generatorAndSend(data);
        } catch (Exception e) {
            LOGGER.error("[邮件消费端]-从队列中获取邮件信息进行发送失败:", e);
        }

    }

}
