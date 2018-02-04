package com.helon.mail.enumeration;

/**
 * @Author: Helon
 * @Description: 优先级枚举值
 * @Data: Created in 2018/2/4 14:17
 * @Modified By:
 */
public enum RedisPriorityQueue {
    //7,8,9隐私、安全、交易
    FAST_QUEUE("fast"),
    //4,5,6 活动、通知类
    NORMAL_QUEUE("normal"),
    //1,2,3 汇总、调查
    DEFER_QUEUE("defer");

    private String code;

    RedisPriorityQueue(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
