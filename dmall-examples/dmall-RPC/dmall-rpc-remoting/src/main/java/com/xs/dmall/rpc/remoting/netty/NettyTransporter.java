package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.ChannelHandler;
import com.xs.dmall.rpc.remoting.Client;
import com.xs.dmall.rpc.remoting.RemotingServer;
import com.xs.dmall.rpc.remoting.Transporter;

/**
 *
 */
public class NettyTransporter implements Transporter {

    public static final String NAME = "netty";

    /**
     * 绑定NettyServer
     *
     * @param url     server url
     * @param handler 通道处理器
     * @return
     */
    @Override
    public RemotingServer bind(URL url, ChannelHandler handler) {
        return new NettyServer(url, handler);
    }

    /**
     * Connect to a server.
     * @param url     server url
     * @param handler 通道处理器
     * @return
     */
    @Override
    public Client connect(URL url, ChannelHandler handler){
        return new NettyClient(url, handler);
    }
}
