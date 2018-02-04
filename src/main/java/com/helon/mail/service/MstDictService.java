package com.helon.mail.service;

import com.helon.mail.entity.MstDict;
import com.helon.mail.mapper.MstDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Helon
 * @Description: 字典service
 * @Data: Created in 2018/1/24 21:48
 * @Modified By:
 */
@Service
public class MstDictService {
    @Resource
    private MstDictMapper mstDictMapper;

    public List<MstDict> findByStatus(int status) throws Exception{
        return mstDictMapper.findByStatus(status);
    }
}
