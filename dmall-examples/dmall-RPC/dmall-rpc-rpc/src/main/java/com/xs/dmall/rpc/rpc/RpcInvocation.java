package com.xs.dmall.rpc.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 调用
 * @author duwei
 */
public class RpcInvocation implements Invocation, Serializable {
    /**
     * 接口名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型数组
     */
    private Class[] paramTypes;
    /**
     * 参数值数组
     */
    private Object[] arguments;

    public RpcInvocation(String serviceName, String methodName, Class[] paramTypes, Object[] arguments) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.arguments = arguments;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "RpcInvocation{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
