package luj.net.api.client;

import luj.net.api.connection.NetReceiveListener;

public interface NetConnection {

  void send(byte[] data);

  void close();

  boolean isClosed();

  /**
   * @see NetReceiveListener.Context#getApplicationParam
   */
  @Deprecated
  <T> T getApplicationParam();
}
