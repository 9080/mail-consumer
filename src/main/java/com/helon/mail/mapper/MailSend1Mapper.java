package com.helon.mail.mapper;
import com.helon.mail.config.database.BaseMapper;
import com.helon.mail.entity.MailSend;

public interface MailSend1Mapper extends BaseMapper<MailSend> {

//    int deleteByPrimaryKey(String sendId);
//
    int insert(MailSend record);
//
//    int insertSelective(MailSend record);
//
    int updateByPrimaryKeySelective(MailSend record);
//
//    int updateByPrimaryKey(MailSend record);
}