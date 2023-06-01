package cn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConMemory {
    public static void main(String[] args) {
        //1.设置jedis连接池的配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(100);
        //2.设置jedis相关链接信息
        final String REMOTEHOST = "10.110.160.12";
        final int PORT = 63178;
        final String PASSWORD = "test";
        //3.设置Jedis连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, REMOTEHOST, PORT);
        //4.获取jedis链接对象
        Jedis jedis = jedisPool.getResource();
        System.out.println("jedis="+jedis);
        //5.链接
        jedis.auth(PASSWORD);
        String info = jedis.info("Memory");
        System.out.println("Memory---------------->" + info);
        //6.切换数据库
        String select = jedis.select(10);
        System.out.println(select);


        /**
         * 1.used_memory:8589737336 由 Redis 分配器分配的内存总量，包含了redis进程内部的开销和数据占用的内存，以字节（byte）为单位
         * 2.used_memory_human:8.00G 以更直观的单位展示分配的内存总量
         ***** 3.used_memory_rss:13926313984 向操作系统申请的内存大小。
         ***** 与 top 、 ps等命令的输出一致。在理想情况下， used_memory_rss 的值应该只比 used_memory 稍微高一点儿。当 rss > used ，且两者的值相差较大时，表示存在（内部或外部的）内存碎片。
         ***** 当 used > rss 时，表示 Redis 的部分内存被操作系统换出到交换空间了，在这种情况下，操作可能会产生明显的延迟
         * 4.used_memory_rss_human:12.97G 以更直观的单位展示向操作系统申请的内存大小
         * 5.used_memory_peak:8590052144 redis的内存消耗峰值(以字节为单位)
         * 6.used_memory_peak_human:8.00G 以更直观的格式返回redis的内存消耗峰值
         * 7.used_memory_peak_perc:100.00% 使用内存达到峰值内存的百分比，即(used_memory/ used_memory_peak) *100%
         * 8.used_memory_overhead:8533733870 Redis为了维护数据集的内部机制所需的内存开销，包括所有客户端输出缓冲区、查询缓冲区、AOF重写缓冲区和主从复制的backlog
         * 9.used_memory_startup:791952 Redis服务器启动时消耗的内存
         * 10.used_memory_dataset:56003466 数据占用的内存大小，即used_memory-used_memory_overhead
         * 11.used_memory_dataset_perc:0.65% 数据占用的内存大小的百分比，100%*(used_memory_dataset/(used_memory-used_memory_startup))
         * 12.allocator_allocated:8589767360
         * 13.allocator_active:10937163776
         * 14.allocator_resident:11065339904
         * 15.total_system_memory:135053119488操作系统内存
         * 16.total_system_memory_human:125.78G以更直观的格式显示操作系统内存
         * 17.used_memory_lua:37888 Lua脚本存储占用的内存
         * 18.used_memory_lua_human:37.00K以更直观的格式显示Lua脚本存储占用的内存
         * 19.used_memory_scripts:0 Lua脚本使用的字节数
         * 20.used_memory_scripts_human:0B
         * 21.number_of_cached_scripts:0
         * 22.maxmemory:8589934592 Redis实例的最大内存配置
         * 23.maxmemory_human:8.00G以更直观的格式显示Redis实例的最大内存配置
         * 24.maxmemory_policy:allkeys-lfu当达到maxmemory时的淘汰策略
         * 25.allocator_frag_ratio:1.27
         * 26.allocator_frag_bytes:2347396416
         * 27.allocator_rss_ratio:1.01
         * 28.allocator_rss_bytes:128176128
         * 29.rss_overhead_ratio:1.26
         * 30.rss_overhead_bytes:2860974080
         ***** 31.mem_fragmentation_ratio>1，则表示redis有部分内存没有用于数据存储，而是被内存碎片消耗，两者相差越大，说明碎片率越严重。
         ***** 当mem_fragmentation_ratio<1时，这种情况一般出现在操作系统把Redis内存交换（Swap）到硬盘导致，出现这种情况时要格外关注，
         * 由于硬盘速度远远慢于内存，Redis性能会变得很差，甚至僵死
        **/


    }
}
