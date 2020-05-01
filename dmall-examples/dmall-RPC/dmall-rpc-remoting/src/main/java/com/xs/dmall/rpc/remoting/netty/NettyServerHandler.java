package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.ChannelHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * NettyServerHandler
 */
public class NettyServerHandler extends ChannelDuplexHandler {

    private final URL url;
    /**
     * NettyServer也有可能注进来，因为NettyServer也实现了ChannelHandler
     */
    private final ChannelHandler handler;

    public NettyServerHandler(URL url, ChannelHandler handler) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(), url, handler);
        handler.received(channel, msg);
    }
}
