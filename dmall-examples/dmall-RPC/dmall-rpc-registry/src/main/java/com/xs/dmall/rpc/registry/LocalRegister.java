package com.xs.dmall.rpc.registry;

/**
 * 本地注册
 */
public interface LocalRegister {

    /**
     * 注册接口
     * @param interfaceName 接口名称
     * @param interfaceImplClass 接口实现类
     */
    void register(String interfaceName, Class interfaceImplClass);

    /**
     * 获取接口实现类
     * @param interfaceName
     * @return
     */
    Class get(String interfaceName);
}
