package com.xs.dmall.rpc.remoting.zookeeper;

import java.util.List;

/**
 * 子节点监听
 */
public interface ChildListener {
    /**
     * 子节点变化
     *
     * @param path     路径
     * @param children 子节点
     */
    void childChanged(String path, List<String> children);
}
