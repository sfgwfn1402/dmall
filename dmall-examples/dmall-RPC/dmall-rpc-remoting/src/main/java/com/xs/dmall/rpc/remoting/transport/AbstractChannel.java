package com.xs.dmall.rpc.remoting.transport;

import com.xs.dmall.rpc.common.entity.URL;
import com.xs.dmall.rpc.remoting.AbstractPeer;
import com.xs.dmall.rpc.remoting.Channel;
import com.xs.dmall.rpc.remoting.ChannelHandler;

/**
 * AbstractChannel
 */
public abstract class AbstractChannel extends AbstractPeer implements Channel {

    public AbstractChannel(URL url, ChannelHandler handler){
        super(url, handler);
    }

}
