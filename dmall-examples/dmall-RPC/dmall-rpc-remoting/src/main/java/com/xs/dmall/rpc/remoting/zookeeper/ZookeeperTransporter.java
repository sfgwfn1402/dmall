package com.xs.dmall.rpc.remoting.zookeeper;

import com.xs.dmall.rpc.common.entity.URL;

public interface ZookeeperTransporter {
    /**
     * 连接zk
     * @param url
     * @return
     */
    ZookeeperClient connect(URL url);

}
