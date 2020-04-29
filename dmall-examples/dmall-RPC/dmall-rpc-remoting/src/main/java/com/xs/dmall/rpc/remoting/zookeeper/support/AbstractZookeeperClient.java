package com.xs.dmall.rpc.remoting.zookeeper.support;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.zookeeper.ChildListener;
import com.xs.dmall.rpc.remoting.zookeeper.DataListener;
import com.xs.dmall.rpc.remoting.zookeeper.StateListener;
import com.xs.dmall.rpc.remoting.zookeeper.ZookeeperClient;

import java.util.List;
import java.util.concurrent.Executor;

public abstract class AbstractZookeeperClient implements ZookeeperClient {

    private final URL url;
    private volatile boolean closed = false;

    public AbstractZookeeperClient(URL url) {
        this.url = url;
    }

    @Override
    public void create(String path, boolean ephemeral) {
        if (ephemeral) {
            createEphemeral(path);
        }
    }

    @Override
    public void create(String path, String content, boolean ephemeral) {
        if (ephemeral) {
            createEphemeral(path, content);
        }
    }

    protected abstract void createEphemeral(String path, String content);

    /**
     * 抽象方法-创建临时节点
     *
     * @param path
     */
    protected abstract void createEphemeral(String path);

    protected abstract void doClose();

    @Override
    public void delete(String path) {

    }

    @Override
    public List<String> getChildren(String path) {
        return null;
    }

    @Override
    public List<String> addChildListener(String path, ChildListener listener) {
        return null;
    }

    @Override
    public void addDataListener(String path, DataListener listener) {

    }

    @Override
    public void addDataListener(String path, DataListener listener, Executor executor) {

    }

    @Override
    public void removeDataListener(String path, DataListener listener) {

    }

    @Override
    public void removeChildListener(String path, ChildListener listener) {

    }

    @Override
    public void addStateListener(StateListener listener) {

    }

    @Override
    public void removeStateListener(StateListener listener) {

    }

    @Override
    public void close() {
        if (closed){
            return;
        }
        closed = true;
        doClose();
    }

    @Override
    public URL getUrl() {
        return null;
    }


    @Override
    public String getContent(String path) {
        return null;
    }
}
