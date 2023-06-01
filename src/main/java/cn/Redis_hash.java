package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.logging.Logger;

public class Redis_hash {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(3000));

        final String REMOTEHOST = "10.110.160.12";
        final int PORT = 63179;
        final String PASSWORD = "loadtest";

        Jedis resource = null;
        Logger logger = null;
        try {
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, REMOTEHOST, PORT);
            resource = jedisPool.getResource();
            resource.auth(PASSWORD);

            String select = resource.select(10);
            System.out.println("返回值:->" + select); //ok

            //判断是否有key field的值
            if (resource.hexists("user:1","city") == true){
                resource.hdel("user:1","city");
                resource.hset("user:1","city","beijing");
            }
            //插入hash
            resource.hset("user:2","name","mike");
            resource.hset("user:2","city","tianjin");
            resource.hset("user:2","age","18");

            System.out.println("user:2的长度"+resource.hlen("user:2"));




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
