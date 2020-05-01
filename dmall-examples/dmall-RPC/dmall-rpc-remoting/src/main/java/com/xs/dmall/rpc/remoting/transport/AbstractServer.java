package com.xs.dmall.rpc.remoting.transport;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.AbstractPeer;
import com.xs.dmall.rpc.remoting.ChannelHandler;

import java.net.InetSocketAddress;

/**
 * 进程抽象服务管理
 * 服务的打开、关闭等
 */
public abstract class AbstractServer extends AbstractPeer {

    private InetSocketAddress bindAddress;

    public AbstractServer(URL url, ChannelHandler handler) {
        super(url, handler);

        bindAddress = new InetSocketAddress(getUrl().getHost(), getUrl().getPort());
        doOpen();
    }

    /**
     * 打开服务，如打开Netty服务
     */
    protected abstract void doOpen();

    public InetSocketAddress getBindAddress() {
        return bindAddress;
    }
}
