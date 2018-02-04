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
//
//    int updateByPrimaryKey(MailSend record);
}