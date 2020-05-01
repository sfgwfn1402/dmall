package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.ChannelHandler;
import com.xs.dmall.rpc.remoting.RemotingServer;
import com.xs.dmall.rpc.remoting.Transporter;

/**
 *
 */
public class NettyTransporter implements Transporter {

    public static final String NAME = "netty";

    @Override
    public RemotingServer bind(URL url, ChannelHandler handler) {
        return new NettyServer(url, handler);
    }
}
