package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class RedisConCPU {
    public static void main(String[] args) {
        //1.设置jedis连接池的配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(100);
        //设置链接毫秒数
        jedisPoolConfig.setMaxWait(Duration.ofMillis(3000));
        //2.设置jedis相关链接信息
        final String REMOTEHOST = "10.110.160.12";
        final int PORT = 63178;
        final String PASSWORD = "test";
        //3.设置Jedis连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, REMOTEHOST, PORT);
        //4.获取jedis链接对象
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis);
        //5.链接
        jedis.auth(PASSWORD);
        String info = jedis.info("CPU");
        System.out.println("Memory---------------->" + info);


        /**
         * 1.Memory----------------># CPU
         * 2.used_cpu_sys:79.949867
         * 3.used_cpu_user:78.695352
         * 4.used_cpu_sys_children:0.005079
         * 5.used_cpu_user_children:0.001916
         **/


    }
}
