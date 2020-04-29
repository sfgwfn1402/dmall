package com.xs.dmall.rpc.registry.zookeeper;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.registry.RemoteRegister;
import com.xs.dmall.rpc.registry.support.AbstractRegistryFactory;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperTransporter;

import java.rmi.Remote;

public class ZookeeperRegistryFactory extends AbstractRegistryFactory {

    private ZookeeperTransporter zookeeperTransporter;

    public void setZookeeperTransporter(ZookeeperTransporter zookeeperTransporter) {
        this.zookeeperTransporter = zookeeperTransporter;
    }

    @Override
    public RemoteRegister createRegistry(URL url){
        return new ZookeeperRemoteRegister(url, zookeeperTransporter);
    }

}
