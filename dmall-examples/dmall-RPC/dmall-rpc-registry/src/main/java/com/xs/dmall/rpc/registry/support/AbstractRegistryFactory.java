package com.xs.dmall.rpc.registry.support;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.registry.RemoteRegister;

public abstract class AbstractRegistryFactory {


    public RemoteRegister getRegistry(URL url){
        RemoteRegister remoteRegister =  createRegistry(url);
        return remoteRegister;
    }
    protected abstract RemoteRegister createRegistry(URL url);
}
