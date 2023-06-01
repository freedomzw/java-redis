package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName demo01
 * @Description TODO
 * @Author 2023/4/24 9:37 PM
 * @Version 1.0
 */
public class demo01 {
    public static void main(String[] args) throws Exception {

        //创建连接的客户端
        String connectString = "127.0.0.1:2181";
        //回话超时，连接上了服务器，但是我们一直不操作，等时间到了再超时,连接到了服务器之后，3秒中不操作就超时
        int sessionTimeoutMs = 3000;
        //连接超时，客户端连接服务，一直都连接不上，尝试3秒钟，一旦超过时间后就不再连接
        int connectionTimeoutMs = 3000;
        //重试策略
        //int baseSleepTimeMs, 失败的时候 失眠多长时间才合适
        // int maxRetries, 最多重试的次数
        // int maxSleepMs，最多睡眠的时间
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1, 3, 1);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        //启动客户端
        client.start();
        //1。使用客户端操作
        //创建一个空节点a，（只能创建一个节点）
        //client.create().forPath("/test2");
        //编码集从大的转化成小的，就会出现数据丢失
        //client.create().forPath("/test3","中文".getBytes(StandardCharsets.UTF_8));
        //创建多层节点,同时创建多层节点
        //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/app2/a","hello".getBytes(StandardCharsets.UTF_8));
        //创建带有序号的，持久化节点,PERSISTENT_SEQUENTIAL带有序号的持久化节点
        //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/app2/a","hello".getBytes(StandardCharsets.UTF_8));

        //5. 创建临时节点（客户端关闭，节点消失），设置延时5秒关闭
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/f");
        //6. 创建临时带序号节点（客户端关闭，节点消失），设置延时5秒关闭
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/f");

        Thread.sleep(5000);
        client.close();
    }
}
