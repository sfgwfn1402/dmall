package com.xs.dmall.rpc.remoting;

import com.xs.dmall.rpc.common.entity.URL;

/**
 * Transporter
 */
public interface Transporter {


    /**
     * Bind a server
     *
     * @param url     server url
     * @param handler 通道处理器
     * @return
     */
    RemotingServer bind(URL url, ChannelHandler handler);

    /**
     * Connect to a server.
     *
     * @param url     server url
     * @param handler 通道处理器
     * @return
     */
    Client connect(URL url, ChannelHandler handler);

}
