package com.xs.dmall.rpc.remoting.zookeeper.support;

import com.alibaba.fastjson.JSON;
import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperTransporter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractZookeeperTransporter implements ZookeeperTransporter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractZookeeperTransporter.class);

    private final Map<String, ZookeeperClient> zookeeperClientMap = new ConcurrentHashMap<>();

    /**
     * share connect for registry
     *
     * <p>
     * Make sure the connection is connected.
     * </p>
     *
     * @param url
     * @return
     */
    @Override
    public ZookeeperClient connect(URL url) {
        logger.info("zookeeper connect"+ JSON.toJSONString(url));
        ZookeeperClient zookeeperClient;

        // address format: {[username:password@]address}
        String address = url.getHost() + ":" + url.getPort();

        zookeeperClient = createZookeeperClient(url);
        writeToClientMap(address, zookeeperClient);
        return zookeeperClient;
    }

    /**
     * write address-ZookeeperClient relationship to Map
     *
     * @param address
     * @param zookeeperClient
     */
    private void writeToClientMap(String address, ZookeeperClient zookeeperClient) {
        zookeeperClientMap.put(address, zookeeperClient);
    }

    protected abstract ZookeeperClient createZookeeperClient(URL url);


}
