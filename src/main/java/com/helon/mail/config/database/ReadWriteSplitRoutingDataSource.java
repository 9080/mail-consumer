package com.helon.mail.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: Helon
 * @Description: 读写数据源分隔路由
 * @Data: Created in 2018/1/21 20:16
 * @Modified By:
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getDataBaseType();
    }
}
