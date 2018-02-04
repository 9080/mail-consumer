package com.helon.mailconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: Helon
 * @Description: 统一配置类
 * @Data: Created in 2018/1/20 22:55
 * @Modified By:
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.helon.mail.*"})
@MapperScan(basePackages = "com.helon.mail.mapper")
//@ImportResource(value = "classpath:config.xml")
public class MainConfig {
}
