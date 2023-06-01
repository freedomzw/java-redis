package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.logging.Logger;

public class Redis_String {

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

            boolean hello = resource.exists("hello");
            System.out.println(hello);
            if (resource.exists("hello") == true) {
                resource.set("hello", "redis_new");
            } else {
                //如果不存在的话使用setnx
                resource.setnx("hello", "redis_new2");
            }
            resource.expire("过期时间秒", 1000);
            resource.pexpire("过期时间毫秒", 1000);
            resource.expireAt("时间戳", 100);
            resource.pexpireAt("毫秒的时间戳", 1000);

            //自增
            resource.incr("name");
            resource.incr("name");
            resource.incr("name2");
            //自减
            resource.decr("name");

            byte [] bytes ={1,2,3,4};
            String test = resource.set("test", "1");
            String test1 = resource.get("test");
            String set = resource.set(bytes, bytes);
            String set1 = String.valueOf(resource.get(bytes));

            System.out.println("->"+test);
            System.out.println("->"+set);
            System.out.println("->"+test1);
            System.out.println("->"+set1);

            resource.strlen("test");
            resource.append("test","aaaaaaaaaa");
            resource.setrange("test",2,"5");


//            resource.mset('a',1,2,4,5,);
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
