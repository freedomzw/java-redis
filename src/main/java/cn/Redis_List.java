package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class Redis_List {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(3000));

        final String REMOTEHOST = "10.110.160.12";
        final int PORT = 63178;
        final String PASSWORD = "test";

        Jedis resource = null;
        Logger logger = null;
        try {
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, REMOTEHOST, PORT);
            resource = jedisPool.getResource();
            resource.auth(PASSWORD);

            String select = resource.select(10);
            System.out.println("返回值:->" + select); //ok

            String setResult = resource.set("hello", "world");
            String getResult = resource.get("hello");
            System.out.println("setResult:->" + setResult);
            System.out.println("getResult:->" + getResult);

            String[] str1 = {"zhangsan", "lisi", "wangwu"};
            long listkey = resource.rpush("listkey", str1);
            System.out.println(listkey);

            //查询list数据类型
            List<String> listkey1 = resource.lrange("listkey", 0, 1);

            System.out.println(listkey1);
            //获得执行索引下标的长度
            System.out.println(resource.llen("listkey"));

            //获得执行索引下标
            System.out.println(resource.lindex("listkey", 0));
            System.out.println(resource.lindex("listkey", 1));
            //查询全部的索引
            System.out.println(resource.lrange("listkey", 0, -1));
            //从左边弹出元素
            System.out.println(resource.lpop("listkey"));
            //从右边弹出元素
            System.out.println(resource.rpop("listkey"));
            String[] str2 = {"zhangsan", "lisi"};
            //删除指定的元素"zhansan"  从右边开始删除两个zhansan count<0从右边开始 count>0从左边开始
            System.out.println(resource.lrem("listkey", -2, "zhangsan"));
            //删除全部"zhangsan",count=0
            resource.lrem("listkey", 0, "zhangsan");
            System.out.println(resource.lrange("listkey", 0, -1));

            //按照索引范围修剪列表,修剪一个列表
            resource.ltrim("listkey", 1, 3);
            //按修剪之后生成的新列表
            System.out.println(resource.lrange("listkey", 0, -1));
            //将列表中的第三个元素设置为python
            String lset = resource.lset("listkey", 2, "python");
            System.out.println(lset);

            logger = Logger.getLogger("LoggerDemo");
        } catch (Exception e) {
            e.printStackTrace();
            logger.fine("细微的信息");
            logger.finer("更细微的信息");
            logger.finest("最细微的信息");
            logger.config("设定方面的信息");
            logger.info("info信息");
        } finally {
            if (resource != null) {
                resource.close();
            }
        }


    }
}
