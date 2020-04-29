package com.xs.dmall.rpc.registry.zookeeper;

import com.alibaba.fastjson.JSON;
import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.registry.RemoteRegister;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperTransporter;

/**
 * zookeeper远程注册
 */
public class ZookeeperRemoteRegister implements RemoteRegister {
    /**
     * zk操作客户端
     * 引入dmall-rpc-remoting模块
     */
    private ZookeeperClient zkClient;

    /**
     * Invisible injection of zookeeper client via IOC/SPI
     * @param url
     * @param zookeeperTransporter
     */
    public ZookeeperRemoteRegister(URL url, ZookeeperTransporter zookeeperTransporter) {
        zkClient = zookeeperTransporter.connect(url);
    }

    @Override
    public void register(String interfaceName, URL host) {
        StringBuffer nodePath = new StringBuffer("/");
        nodePath.append(interfaceName).append("/").append(JSON.toJSONString(host));
        zkClient.create(nodePath.toString(), "111", true);
    }

    @Override
    public URL getRadomURL(String interfaceName) {
        return null;
    }
}
