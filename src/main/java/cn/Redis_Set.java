package cn;

import com.google.gson.stream.JsonToken;
import netscape.javascript.JSUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Logger;

public class Redis_Set {

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
            System.out.println("返回值:--->" + select); //ok

            String setResult = resource.set("hello", "world");
            String getResult = resource.get("hello");
            System.out.println("setResult:--->" + setResult);
            System.out.println("getResult:--->" + getResult);

            //添加元素
            long sadd = resource.sadd("myset", "a", "b", "c");
            long sadd1 = resource.sadd("myset", "a", "b");
            System.out.println(sadd);
            System.out.println(sadd1);

            //删除元素,删除成功返回删除的个数
            System.out.println(resource.srem("myset", "a", "b"));
            //删除失败，返回0
            System.out.println(resource.srem("myset", "hello"));

            //计算元素的个数
            long myset = resource.scard("myset");
            System.out.println("元素的个数是:" + myset);
            //判断元素是否在集合中
            boolean myset1 = resource.sismember("myset", "c");
            System.out.println("集合中是否存在这个元素: " + myset1);

            long mysite2 = resource.sadd("mysite2", "www.baidu.com", "www.sina.com", "www.goolge.com");
            Set<String> mysite21 = resource.smembers("mysite2");
            System.out.println("smembers的内容:" + mysite21);

            //集合间的操作
            resource.sadd("user:1:follow", "it", "music", "his", "sports");
            resource.sadd("user:2:follow", "it", "news", "ent", "sports");

            //集合的交集
            Set<String> sinter = resource.sinter("user:1:follow", "user:2:follow");
            System.out.println("交集:" + sinter);

            //多个集合的并集
            Set<String> sunion = resource.sunion("user:1:follow", "user:2:follow");
            System.out.println("并集:" + sunion);

            //差集
            Set<String> sdiff = resource.sdiff("user:1:follow", "user:2:follow");
            System.out.println("差集:" + sdiff);

            //查看内部编码
            System.out.println(resource.objectEncoding("sdiff"));
            System.out.println(resource);



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
