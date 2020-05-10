package com.xs.dmall.rpc.remoting;

import java.net.InetSocketAddress;

/**
 * Channel
 */
public interface Channel {

    /**
     * get remote address
     * @return
     */
    InetSocketAddress getRemoteAddress();

    /**
     * is connected
     * @return
     */
    boolean isConnected();

    /**
     * send message.
     *
     * @param message
     */
    void send(Object message);

    /**
     * close the channel
     */
    void close();

}
