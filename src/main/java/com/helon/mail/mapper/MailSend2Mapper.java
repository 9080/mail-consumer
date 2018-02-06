package com.helon.mail.mapper;


import com.helon.mail.config.database.BaseMapper;
import com.helon.mail.entity.MailSend;

public interface MailSend2Mapper extends BaseMapper<MailSend> {

//    int deleteByPrimaryKey(String sendId);
//
    int insert(MailSend record);
//
//    int insertSelective(MailSend record);
//
//    MailSend selectByPrimaryKey(String sendId);
//
    int updateByPrimaryKeySelective(MailSend record);
    /**
     * @Author: Helon
     * @Description: 乐观锁更新操作
     * @param record
     * @Data: 2018/2/6 21:42
     * @Modified By:
     */
    int updateByPrimaryKeyAndVersion(MailSend record);
//
//    int updateByPrimaryKey(MailSend record);
}