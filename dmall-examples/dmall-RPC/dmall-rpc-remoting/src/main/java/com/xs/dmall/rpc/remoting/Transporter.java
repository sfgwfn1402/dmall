package com.xs.dmall.rpc.remoting;

import com.xs.dmall.rpc.common.entity.URL;

/**
 * Transporter
 */
public interface Transporter {


    /**
     * Bind a server
     * @param url server url
     * @param handler
     * @return
     */
    RemotingServer bind(URL url, ChannelHandler handler);



}
