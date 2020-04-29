package com.xs.dmall.rpc.remoting.zookeeper.curator;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.zookeeper.support.AbstractZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.nio.charset.Charset;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CuratorZookeeperClient extends AbstractZookeeperClient {

    static final Charset CHARSET = Charset.forName("UTF-8");

    private final CuratorFramework client;

    public CuratorZookeeperClient(URL url) {
        super(url);
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(url.getHost() + ":" + url.getPort())
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectionTimeoutMs(10000)
                .sessionTimeoutMs(10000)
                .namespace("rpc");
        client = builder.build();
        client.start();

        try {
            boolean connected = client.blockUntilConnected(10000, MILLISECONDS);
            if (!connected) {
                throw new IllegalStateException("zookeeper not connected");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 创建临时节点
     *
     * @param path
     */
    @Override
    protected void createEphemeral(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    protected void doClose() {
        client.close();
    }

    /**
     * 创建临时节点并设置数据
     *
     * @param path
     */
    @Override
    protected void createEphemeral(String path, String data) {
        byte[] dataBytes = data.getBytes(CHARSET);
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, dataBytes);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }
}
