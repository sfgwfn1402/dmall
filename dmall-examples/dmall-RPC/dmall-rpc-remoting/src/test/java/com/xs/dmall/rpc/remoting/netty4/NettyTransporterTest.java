package com.xs.dmall.rpc.remoting.netty4;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.common.utils.NetUtils;
import com.xs.dmall.rpc.remoting.Channel;
import com.xs.dmall.rpc.remoting.RemotingServer;
import com.xs.dmall.rpc.remoting.netty.NettyTransporter;
import com.xs.dmall.rpc.remoting.transport.ChannelHandlerAdapter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NettyTransporterTest {
    Logger logger = LoggerFactory.getLogger(NettyTransporter.class);

    /**
     * 绑定netty4
     */
    @Test
    public void shouldAbleToBindNetty4(){
        int port = NetUtils.getAvailablePort();
        RemotingServer server = new NettyTransporter().bind(new URL("127.0.0.1", port), new ChannelHandlerAdapter());
        assertTrue(server.isBound());

    }

    @Test
    public void shouldConnectToNetty4Server(){
        final CountDownLatch lock = new CountDownLatch(1);

        int port = NetUtils.getAvailablePort();
        URL url = new URL("127.0.0.1", port);

        /**
         * 绑定NettyServer
         */
        new NettyTransporter().bind(url, new ChannelHandlerAdapter(){
            @Override
            public void connected(Channel channel) {
                lock.countDown();
            }
        });

        /**
         * netty客户端连接到netty服务端
         */
        new NettyTransporter().connect(url, new ChannelHandlerAdapter(){

            @Override
            public void sent(Channel channel, Object message) {
                channel.send(message);
                channel.close();
            }
        });
    }
}
