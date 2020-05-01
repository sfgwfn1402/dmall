package com.xs.dmall.rpc.remoting.netty;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.remoting.ChannelHandler;
import com.xs.dmall.rpc.remoting.transport.AbstractChannel;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * NettyChannel维护通道的缓存
 */
final class NettyChannel extends AbstractChannel {

    private static final Logger logger = LoggerFactory.getLogger(NettyChannel.class);

    /**
     * netty通道和rpc通道的缓存
     */
    private static final ConcurrentMap<Channel, NettyChannel> CHANNEL_MAP = new ConcurrentHashMap();

    /**
     * netty channel
     */
    private final Channel channel;

    /**
     * netty通道是否连接有效
     */
    private final AtomicBoolean active = new AtomicBoolean(false);

    /**
     * @param channel netty channel
     * @param url
     * @param handler 包含netty处理程序的rpc处理程序  (也有可能传NettyServer)
     */
    private NettyChannel(Channel channel, URL url, ChannelHandler handler) {
        super(url, handler);
        if (channel == null) {
            throw new IllegalArgumentException("netty channel == null");
        }
        this.channel = channel;
    }

    /**
     * 通过通道缓存从netty通道获取rpc通道。
     *
     * @param channel netty channel
     * @param url     url
     * @param handler 包含netty处理程序的rpc处理程序  (也有可能传NettyServer)
     * @return
     */
    public static NettyChannel getOrAddChannel(Channel channel, URL url, ChannelHandler handler) {
        if (channel == null) {
            return null;
        }
        NettyChannel nettyChannel = CHANNEL_MAP.get(channel);
        if (nettyChannel == null) {
            //rpc通道
            NettyChannel nc = new NettyChannel(channel, url, handler);
            if (channel.isActive()) {
                nettyChannel = CHANNEL_MAP.putIfAbsent(channel, nc);
            }
        }
        return nettyChannel;
    }
}
