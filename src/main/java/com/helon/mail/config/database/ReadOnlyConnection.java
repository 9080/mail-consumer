package com.helon.mail.config.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Helon
 * @Description: 自定义注解-切换主从数据库
 * @Data: Created in 2018/1/21 20:43
 * @Modified By:
 */
//target:注解的作用目标
//ElementType.TYPE:接口、类、枚举、注解
//ElementType.FIELD:字段、枚举的常量
//ElementType.METHOD:方法
//ElementType.CONSTRUCTOR:构造函数
//ElementType.LOCAL_VARIABLE:局部变量
//ElementType.ANNOTATION_TYPE:注解
//ElementType.PACKAGE:包
@Target({ElementType.METHOD, ElementType.TYPE})
//保留策略：
// RetentionPolicy.SOURCE:注解只保留在源文件，编译成class文件时候，注解被遗弃；
//RetentionPolicy.CLASS:注解被保留在class文件，但jvm加载class时候会被遗弃，默认值；
//RetentionPolicy.CLASS:注解不仅被保存到class文件中，jvm加载之后仍然存在；
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnlyConnection {
    //@Document:说明该注解将被包含在javadoc中
    //@Inherited:说明子类可以继承父类中的该注解
}
