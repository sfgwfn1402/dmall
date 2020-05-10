package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.remoting.Channel;
import com.xs.dmall.rpc.remoting.ChannelHandler;
import com.xs.dmall.rpc.remoting.Constants;
import com.xs.dmall.rpc.remoting.transport.AbstractClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import static com.xs.dmall.rpc.remoting.netty.NettyEventLoopFactory.eventLoopGroup;
import static com.xs.dmall.rpc.remoting.netty.NettyEventLoopFactory.socketChannelClass;

public class NettyClient extends AbstractClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private static final EventLoopGroup NIO_EVENT_LOOP_GROUP = eventLoopGroup(Constants.DEFAULT_IO_THREADS, "NettyClientWorker");

    private Bootstrap bootstrap;

    public NettyClient(URL url, ChannelHandler handler) {
        super(url, handler);
    }

    @Override
    protected Channel getChannel() {
        return null;
    }

    @Override
    protected void doOpen() {
        final NettyClientHandler nettyClientHandler = new  NettyClientHandler(getUrl(), this);

        bootstrap = new Bootstrap();
        bootstrap.group(NIO_EVENT_LOOP_GROUP);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.channel(socketChannelClass());

        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("handler", nettyClientHandler);
            }
        });
    }

    @Override
    protected void doConnect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void send(Object message) {

    }
}
