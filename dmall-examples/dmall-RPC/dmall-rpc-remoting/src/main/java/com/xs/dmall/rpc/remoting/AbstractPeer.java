package com.xs.dmall.rpc.remoting;

import com.xs.dmall.rpc.common.entity.URL;

/**
 * AbstractPeer
 * 监视channel作用，查看channel的状态，如关闭情况等
 */
public abstract class AbstractPeer implements ChannelHandler {

    private final ChannelHandler handler;

    private volatile URL url;

    /**
     * 进程正在关闭或已经关闭完成
     */
    private volatile boolean closing;

    private volatile boolean closed;

    public AbstractPeer(URL url, ChannelHandler handler) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.handler = handler;
        this.url = url;
    }

    @Override
    public void connected(Channel channel) {
        if (closed) {
            return;
        }
        handler.connected(channel);
    }

    @Override
    public void received(Channel channel, Object message) {
        if (closed) {
            return;
        }
        handler.received(channel, message);
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        this.url = url;
    }
}
