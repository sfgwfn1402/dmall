package com.xs.dmall.rpc.remoting.netty4;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.common.utils.NetUtils;
import com.xs.dmall.rpc.remoting.RemotingServer;
import com.xs.dmall.rpc.remoting.netty.NettyTransporter;
import com.xs.dmall.rpc.remoting.transport.ChannelHandlerAdapter;
import org.junit.jupiter.api.Test;

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
    public void  shouldConnectToNetty4Server(){

    }
}
