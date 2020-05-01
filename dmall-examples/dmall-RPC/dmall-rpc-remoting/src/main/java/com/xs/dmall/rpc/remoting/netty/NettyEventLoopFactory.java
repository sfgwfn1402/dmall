package com.xs.dmall.rpc.remoting.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ThreadFactory;

public class NettyEventLoopFactory {

    /**
     * 创建EventLoopGroup
     *
     * @param threads           线程数
     * @param threadFactoryName 线程工厂名称
     * @return
     */
    public static EventLoopGroup eventLoopGroup(int threads, String threadFactoryName) {
        ThreadFactory threadFactory = new DefaultThreadFactory(threadFactoryName, true);

        int i = shouldEpoll();
        if (i == 1) {
            return new EpollEventLoopGroup(threads, threadFactory);
        } else if (i == 2) {
            return new KQueueEventLoopGroup(threads, threadFactory);
        } else {
            return new NioEventLoopGroup(threads, threadFactory);
        }
    }

    /**
     * 根据操作系统返回SocketChannel
     *
     * @return
     */
    public static Class<? extends SocketChannel> socketChannelClass() {
        int i = shouldEpoll();
        if (i == 1) {
            return EpollSocketChannel.class;
        } else if (i == 2) {
            return KQueueSocketChannel.class;
        } else {
            return NioSocketChannel.class;
        }
    }

    /**
     * 根据操作系统返回serverSocketChannel
     *
     * @return
     */
    public static Class<? extends ServerSocketChannel> serverSocketChannelClass() {
        int i = shouldEpoll();
        if (i == 1) {
            return EpollServerSocketChannel.class;
        } else if (i == 2) {
            return KQueueServerSocketChannel.class;
        } else {
            return NioServerSocketChannel.class;
        }
    }

    /**
     * 操作系统是否支持epoll
     *
     * @return
     */
    private static int shouldEpoll() {
        String osName = System.getProperty("os.name");
//        if (osName.toLowerCase().contains("linux") && Epoll.isAvailable()) {
//            return 1;
//        }
//        if (osName.toLowerCase().contains("mac") && KQueue.isAvailable()) {
//            return 2;
//        }
        return 0;
    }

}
