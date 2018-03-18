package com.helon.mail.config.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Author: Helon
 * @Description: 只读数据源拦截器
 *      实现Ordered接口，重写getOrder()方法，return值越小优先级越高，表示能优先加载该拦截器
 *      实现Ordered接口相比没有实现的，优先级都要高，不管顺序值有多大
 * @Data: Created in 2018/1/21 20:56
 * @Modified By:
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);

    @Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
        try {
            logger.info("--------------set database connection read only----------------");
            DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            DataBaseContextHolder.clearDataBaseType();
            logger.info("--------------clear database connection-------------");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
