package com.xs.dmall.rpc.remoting.zookeeper;

import org.apache.zookeeper.Watcher;

public enum EventType {
    None(-1),
    /**
     * 节点创建
     */
    NodeCreated(1),
    /**
     * 节点删除
     */
    NodeDeleted(2),
    /**
     * 节点数据变化
     */
    NodeDataChanged(3),
    /**
     * 节点子节点变化
     */
    NodeChildrenChanged(4),
    /**
     * 连接挂起
     */
    CONNECTION_SUSPENDED(11),
    /**
     * 连接重连
     */
    CONNECTION_RECONNECTED(12),
    /**
     * 连接丢失
     */
    CONNECTION_LOST(12),
    /**
     * 连接初始化
     */
    INITIALIZED(10);


    private int intValue;

    EventType(int intValue) {
        this.intValue = intValue;
    }

    public static Watcher.Event.EventType fromInt(int intValue){
        switch (intValue){
            case -1:
                return Watcher.Event.EventType.None;
            case 1:
                return Watcher.Event.EventType.NodeCreated;
            case 2:
                return Watcher.Event.EventType.NodeDeleted;
            case 3:
                return Watcher.Event.EventType.NodeDataChanged;
            case 4:
                return Watcher.Event.EventType.NodeChildrenChanged;
            default:
                throw new RuntimeException("Invalid integer value for conversion to EventType");


        }
    }
}
