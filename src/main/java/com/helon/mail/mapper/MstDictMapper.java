package com.helon.mail.mapper;

import com.helon.mail.config.database.BaseMapper;
import com.helon.mail.entity.MstDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MstDictMapper extends BaseMapper<MstDict> {
	
//	List<MstDict> findByStatus(Map<String, Object> params);

	List<MstDict> findByStatus(@Param("status") Integer status);

}