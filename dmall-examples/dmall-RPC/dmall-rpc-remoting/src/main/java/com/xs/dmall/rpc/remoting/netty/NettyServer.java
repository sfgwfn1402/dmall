package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.remoting.Constants;
import com.xs.dmall.rpc.remoting.RemotingServer;
import com.xs.dmall.rpc.remoting.transport.AbstractServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * NettyServer
 */
public class NettyServer extends AbstractServer implements RemotingServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * netty server bootstrap.
     */
    private ServerBootstrap bootstrap;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    /**
     * the boss channel that receive connections and dispatch these to worker channel.
     */
    private Channel channel;

    public NettyServer(URL url, com.xs.dmall.rpc.remoting.ChannelHandler channelHandler) {
        super(url, channelHandler);
    }

    /**
     * 启动Netty服务
     */
    @Override
    protected void doOpen() {
        bootstrap = new ServerBootstrap();

        bossGroup = NettyEventLoopFactory.eventLoopGroup(1, "NettyServerBoss");
        workerGroup = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "NettyServerWorker");

        final NettyServerHandler nettyChannelHandler = new NettyServerHandler(getUrl(), this);

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NettyEventLoopFactory.serverSocketChannelClass());
        bootstrap.option(ChannelOption.SO_REUSEADDR, Boolean.TRUE);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                ch.pipeline().addLast("encoder", new ObjectEncoder());
                ch.pipeline().addLast("handler", nettyChannelHandler);
            }
        });

        // bind
        ChannelFuture channelFuture = bootstrap.bind(getBindAddress());
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
    }

    @Override
    public boolean isBound() {
        return channel.isActive();
    }
}
