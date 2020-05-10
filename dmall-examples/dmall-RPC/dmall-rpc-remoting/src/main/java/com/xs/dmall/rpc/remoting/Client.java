package com.xs.dmall.rpc.remoting;

/**
 * Remoting Client.
 */
public interface Client extends Channel{

    void reconnect();
}
