package com.helon.mailconsumer;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/1/28 11:06
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailConsumerApplication.class)
public class JedisTemplateTest {
    //单个实例
    private static Jedis jedis;
    //分片
    private static ShardedJedis shard;
    //池化
    private static ShardedJedisPool pool;

    /**
     * @Author: Helon
     * @Description: 提前初始化Jedis连接池
     * @Data: 2018/1/28 15:29
     * @Modified By:
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //单个节点
        jedis = new Jedis("47.93.195.131", 6379);
        //分片
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("47.93.195.131", 6379));
        shard = new ShardedJedis(shards);
        //池化
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(100);//最大连接数
        genericObjectPoolConfig.setMaxIdle(20);//最大空间数
        genericObjectPoolConfig.setMaxWaitMillis(-1);//最大等待时间
        genericObjectPoolConfig.setTestOnBorrow(true);//是否提前对连接进行校验
        pool = new ShardedJedisPool(genericObjectPoolConfig, shards);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        jedis.disconnect();
        shard.disconnect();
        pool.destroy();
    }

    @Test
    public void testString() {
        jedis.set("name", "张三");
        System.out.println(jedis.get("name"));
        jedis.append("name", "和王五");//拼接字符串
        System.out.println(jedis.get("name"));
        jedis.del("name");//删除name键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name", "赵六", "age", "29", "sex", "男");
        jedis.incr("age");//对年龄进行加1操作
        System.out.println(jedis.get("name")+"-"+jedis.get("age")+"-"+jedis.get("sex"));
    }

    /**
     * @Author: Helon
     * @Description: 测试Hash数据类型
     * @Data: 2018/1/28 17:13
     * @Modified By:
     */
    @Test
    public void testHash(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "22");
        map.put("sex", "女");
        //key为user，field为name、age、sex
        jedis.hmset("user", map);
        //取出user对象多个属性值
        List<String> retList = jedis.hmget("user","name", "age", "sex");
        System.out.println(retList);
        //删除mnap中的某个key
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age"));//因为删除了，所以返回null
        System.out.println(jedis.hlen("user"));//返回key为user的键中存放的值的个数
        System.out.println(jedis.exists("user"));//是否存在key为user的记录
        System.out.println(jedis.hkeys("user"));//返回user中的所有key
        System.out.println(jedis.hvals("user"));//返回user中所有value值
        Iterator<String> iterable = jedis.hkeys("user").iterator();
        //遍历所有key
        while (iterable.hasNext()) {
            String key = iterable.next();
            System.out.println(key+":"+jedis.hmget("user", key));
        }
    }

    @Test
    public void testList() throws Exception{
        //开始前，先移除List所有的内容
        jedis.del("testList");
        //按从左边第一位到最后一位查看list的所有元素，返回的是一个List<String>
        System.out.println(jedis.lrange("testList",0, -1));
        jedis.lpush("testList", "java", "C++", "hibernate");
        System.out.println(jedis.lrange("testList",0,-1));
        jedis.del("testList");
        jedis.rpush("testList2", "qianbi");
        jedis.rpush("testList2", "xiangpi");
        jedis.rpush("testList2", "wenjuhe");
        System.out.println(jedis.lrange("testList2", 0, -1));
    }

    /**
     * @Author: Helon
     * @Description: 测试set数据类型
     * @Data: 2018/1/28 17:36
     * @Modified By:
     */
    @Test
    public void testSet() {
        jedis.sadd("account", "张三", "李四", "王五", "赵六", "周八");
        //查看所有元素，返回是一个Set<String>集合，无序的
        System.out.println(jedis.smembers("account"));
        //删除其中一个元素，删除成功则返回1，失败返回0
        System.out.println(jedis.srem("account", "周八1"));
        //查看删除后的set集合
        System.out.println(jedis.smembers("account"));
        //判断张三是否为其中的一个元素，返回true or false
        System.out.println(jedis.sismember("account", "张三"));
        //随机弹出一个元素
        System.out.println(jedis.srandmember("account"));
        //返回集合的元素个数
        System.out.println(jedis.scard("account"));

    }

    @Test
    public void testRLpush() throws Exception {
        jedis.del("a");
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));//排序
        System.out.println(jedis.lrange("a", 0, -1));
    }
}
