package com.xs.dmall.rpc.remoting.transport;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.common.logger.Logger;
import com.xs.dmall.rpc.common.logger.LoggerFactory;
import com.xs.dmall.rpc.common.utils.NetUtils;
import com.xs.dmall.rpc.remoting.*;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractClient extends AbstractPeer implements Client {

    private static final Logger logger = LoggerFactory.getLogger(AbstractClient.class);
    private final Lock connectLock = new ReentrantLock();

    public AbstractClient(URL url, ChannelHandler handler) {
        super(url, handler);
        try {
            doOpen();
        } catch (Throwable t) {
            close();
//            throw new RemotingException();
        }

        try {
            connect();
            if (logger.isInfoEnabled()) {
                logger.info("Start " + getClass().getSimpleName() + " " + NetUtils.getLocalAddress() + " connect to the server" + getRemoteAddress());
            }
        } catch (Exception e) {
            close();
        }
    }


    protected void connect() {
        connectLock.lock();
        try {
            // Connect to server
            doConnect();
        }finally {
            connectLock.unlock();
        }
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void reconnect() {

    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        Channel channel = getChannel();
        if (channel == null) {
            return getUrl().toInetSocketAddress();
        }
        return channel.getRemoteAddress();
    }

    /**
     * Get the connected channel
     *
     * @return
     */
    protected abstract Channel getChannel();

    /**
     * Open client
     */
    protected abstract void doOpen();

    /**
     * Connect to server
     */
    protected abstract void doConnect();
}
