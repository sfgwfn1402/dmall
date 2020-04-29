package com.xs.dmall.rpc.registry.zookeeper;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.registry.RemoteRegister;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;
import com.xs.dmall.rpc.remoting.zookeeper.curator.CuratorZookeeperTransporter;
import com.xs.dmall.rpc.remoting.zookeeper.support.AbstractZookeeperTransporter;
import org.apache.curator.test.TestingServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ZookeeperRemoteRegisterTest {
    Logger logger = LoggerFactory.getLogger(AbstractZookeeperTransporter.class);
    private TestingServer zkServer;
    private ZookeeperClient zookeeperClient;
    private AbstractZookeeperTransporter abstractZookeeperTransporter;
    private int zkServerPort;

    @BeforeEach
    void setUp() {
        abstractZookeeperTransporter = new CuratorZookeeperTransporter();
        zookeeperClient = abstractZookeeperTransporter.connect(new URL("127.0.0.1", 2181));

    }

    @AfterEach
    void tearDown() throws IOException {
        zkServer.stop();
    }

    @Test
    void register() {
        URL url = new URL("127.0.0.1", 2181);
        RemoteRegister remoteRegister = new ZookeeperRemoteRegister(url, abstractZookeeperTransporter);
        remoteRegister.register("testInterface",url);

        zookeeperClient.close();

    }

    @Test
    void getRadomURL() {
    }
}
