package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.logging.Logger;

public class Redis_zSet {
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
            //集合内-添加成员
            int[] scores = {1,91,200,220,250};
            String [] members = {"kiri","mike","frank","tim","gay"};
            resource.zadd("user:ranking", 251, "tom");
//            resource.zadd("user:ranking", scores ,members);


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
