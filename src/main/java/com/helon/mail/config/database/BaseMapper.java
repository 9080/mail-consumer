package com.helon.mail.config.database;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B>base-sys<BR>
 * <B>中文类名：</B>BaseMapper<BR>
 * <B>概要说明：</B>基础数据库服务<BR>
 * @author bhz
 * @since 2017年2月8日 下午2:42:49
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
