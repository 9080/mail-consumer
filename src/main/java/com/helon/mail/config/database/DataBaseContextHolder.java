package com.helon.mail.config.database;

/**
 * @Author: Helon
 * @Description: 区分主从数据源
 * @Data: Created in 2018/1/21 18:25
 * @Modified By:
 */
public class DataBaseContextHolder {

    /**
     * @Author: Helon
     * @Description: 主从枚举值
     * @Data: 2018/1/22 10:14
     * @Modified By:
     */
    public enum DataBaseType {
        MASTER, SLAVE
    }
    /**将标识放入到ThreadLocal中，防止并发请求出现主从错乱*/
    private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<>();
    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        contextHolder.set(dataBaseType);
    }

    public static DataBaseType getDataBaseType(){
        return contextHolder.get() == null ? DataBaseType.MASTER : contextHolder.get();
    }

    public static void clearDataBaseType(){
        contextHolder.remove();
    }
}
