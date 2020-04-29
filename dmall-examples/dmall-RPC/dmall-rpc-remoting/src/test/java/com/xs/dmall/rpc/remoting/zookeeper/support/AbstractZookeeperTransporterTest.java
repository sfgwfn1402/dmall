package com.xs.dmall.rpc.remoting.zookeeper.support;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;
import com.xs.dmall.rpc.remoting.zookeeper.curator.CuratorZookeeperTransporter;
import org.apache.curator.test.TestingServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractZookeeperTransporterTest {
    Logger logger = LoggerFactory.getLogger(AbstractZookeeperTransporter.class);
    private TestingServer zkServer;
    private ZookeeperClient zookeeperClient;
    private AbstractZookeeperTransporter abstractZookeeperTransporter;
    private int zkServerPort;

    /**
     * 通过cruator服务连接到zookeeper服务
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        zkServerPort = 8987;
        zkServer = new TestingServer(zkServerPort, true);
        zookeeperClient = new CuratorZookeeperTransporter().connect(new URL("127.0.0.1", 2181));
    }

    /**
     *
     * @throws IOException
     */
    @AfterEach
    void tearDown() throws IOException {
        zkServer.stop();
    }

    @Test
    void connect() {
        logger.info("111");
        assertNotNull(zookeeperClient);
        zookeeperClient.close();
    }

    @Test
    void createZookeeperClient() {
        zookeeperClient.create("/rpc/abc", true);
        List<String> children = zookeeperClient.getChildren("/rpc");
        assertEquals(1, children.size(),"子节点创建错误");
    }
}
