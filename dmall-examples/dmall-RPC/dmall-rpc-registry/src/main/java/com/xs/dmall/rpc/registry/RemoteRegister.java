package com.xs.dmall.rpc.registry;

import com.xs.dmall.rpc.common.entity.URL;

public interface RemoteRegister {
    /**
     * 注册到远程注册中心
     * @param interfaceName 接口名称
     * @param host 地址
     */
    void register(String interfaceName, URL host);

    /**
     * 获取服务地址信息
     * @param interfaceName 接口名称
     * @return
     */
    URL getRadomURL(String interfaceName);
}
