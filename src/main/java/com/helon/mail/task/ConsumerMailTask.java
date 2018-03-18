package com.helon.mail.task;

import com.helon.mail.entity.MailSend;
import com.helon.mail.enumeration.RedisPriorityQueue;
import com.helon.mail.service.MailSendService;
import com.helon.mail.utils.FastJsonConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/2/4 20:53
 * @Modified By:
 */
@Component
public class ConsumerMailTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMailTask.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MailSendService mailSendService;
    /**
     * @Author: Helon
     * @Description: initialDelay表示服务器启动延迟5开始启动
     *          fixedDelay表示每隔2秒执行一次
     *          高优先级：隐私、安全、交易
     * @Data: 2018/2/4 21:01
     * @Modified By:
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void intervalFast() {
        LOGGER.info("[高优先级定时任务]-开始执行...");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String ret = opsForList.leftPop(RedisPriorityQueue.FAST_QUEUE.getCode());
        if (!StringUtils.isBlank(ret)) {
            mailSendService.sendMessageOrder(FastJsonConvertUtil.convertJSONToObject(ret, MailSend.class));
        }
    }

    /**
     * @Author: Helon
     * @Description: 正常优先级 10秒间隔执行一次
     *  一般是活动、通知类
     * @Data: 2018/3/18 15:02
     * @Modified By:
     */
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void intervalNormal(){
        LOGGER.info("[一般优先级定时任务]-开始执行...");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String ret = opsForList.leftPop(RedisPriorityQueue.NORMAL_QUEUE.getCode());
        if (!StringUtils.isBlank(ret)) {
            mailSendService.sendMessageOrder(FastJsonConvertUtil.convertJSONToObject(ret, MailSend.class));
        }
    }

    /**
     * @Author: Helon
     * @Description: 低优先级 30秒间隔执行一次
     *  一般是汇总、调查
     * @Data: 2018/3/18 15:02
     * @Modified By:
     */
    @Scheduled(initialDelay = 30000, fixedDelay = 30000)
    public void intervalDefer(){
        LOGGER.info("[低优先级定时任务]-开始执行...");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String ret = opsForList.leftPop(RedisPriorityQueue.DEFER_QUEUE.getCode());
        if (!StringUtils.isBlank(ret)) {
            mailSendService.sendMessageOrder(FastJsonConvertUtil.convertJSONToObject(ret, MailSend.class));
        }
    }
}
