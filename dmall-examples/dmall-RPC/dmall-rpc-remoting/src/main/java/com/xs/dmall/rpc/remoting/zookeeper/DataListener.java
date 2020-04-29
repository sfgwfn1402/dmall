package com.xs.dmall.rpc.remoting.zookeeper;

/**
 * 数据监听
 */
public interface DataListener {
    /**
     * 数据变化
     *
     * @param path      路径
     * @param value     值
     * @param eventType 事件类型
     */
    void dataChanged(String path, Object value, EventType eventType);
}
