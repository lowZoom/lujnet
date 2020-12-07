package luj.net.api.client;

import luj.net.api.connection.NetReceiveListener;

public interface NetConnection {

  interface Config {

    Config host(String host);

    Config port(int port);

    Config connectTimeoutMillis(int timeoutMs);

    Config receiveListener(NetReceiveListener listener);
  }

  void send(byte[] data);

  void close();

  boolean isClosed();

  /**
   * @see NetReceiveListener.Context#getApplicationParam
   */
  @Deprecated
  <T> T getApplicationParam();
}
