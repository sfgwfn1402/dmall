package com.xs.dmall.rpc.remoting.zookeeper.curator;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;
import com.xs.dmall.rpc.remoting.zookeeper.support.AbstractZookeeperTransporter;

public class CuratorZookeeperTransporter extends AbstractZookeeperTransporter {
    @Override
    protected ZookeeperClient createZookeeperClient(URL url) {
        return new CuratorZookeeperClient(url);
    }
}
