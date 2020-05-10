package com.xs.dmall.rpc.remoting;

/**
 * ChannelHandler
 * channel的连接，接收，发送信息等
 */
public interface ChannelHandler {

    /**
     * on channel connected.
     * @param channel
     */
    void connected(Channel channel);

    /**
     * on messsage received.
     * @param channel
     * @param message
     */
    void received(Channel channel, Object message);

    /**
     * on message sent
     * @param channel
     * @param message
     */
    void sent(Channel channel, Object message);

}
