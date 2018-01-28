package com.helon.mail.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.activation.DataContentHandler;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.AbstractMap;

/**
 * @Author: Helon
 * @Description: mybatis配置类
 * @Data: Created in 2018/1/21 20:03
 * @Modified By:
 */
@Configuration  //标识当前类为配置类
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

    @Resource(name="masterDataSource")
    private DataSource masterDataSource;
    @Resource(name="slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }

    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        AbstractMap targetDataSource = new ClassLoaderRepository.SoftHashMap();
        targetDataSource.put(DataBaseContextHolder.DataBaseType.MASTER, masterDataSource);
        targetDataSource.put(DataBaseContextHolder.DataBaseType.SLAVE, slaveDataSource);
        //默认数据源
        proxy.setDefaultTargetDataSource(masterDataSource);
        //装入两个主从数据源
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }
}
