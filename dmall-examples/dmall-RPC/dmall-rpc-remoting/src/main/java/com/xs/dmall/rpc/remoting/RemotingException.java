package com.xs.dmall.rpc.remoting;

import java.net.InetSocketAddress;

public class RemotingException extends Exception {

    private InetSocketAddress localAddress;

    private InetSocketAddress remoteAddress;

    public RemotingException(String message, Throwable cause, InetSocketAddress localAddress, InetSocketAddress remoteAddress) {
        super(message, cause);
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }
}
