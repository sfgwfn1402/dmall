package com.xs.dmall.rpc.rpc;

import java.io.Serializable;

/**
 * 调用
 */
public interface Invocation {
    /**
     * 获取接口名称
     */
    String getServiceName();
    /**
     * 获取方法名称
     */
     String getMethodName();
    /**
     * 获取参数类型数组
     */
     Class[] getParamTypes();
    /**
     * 获取参数值数组
     */
     Object[] getArguments();

}
