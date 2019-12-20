package luj.net.api.client;

import luj.net.api.data.NetReceiveListener;

public interface NetConnection {

  void send(byte[] data);

  void close();

  /**
   * @see NetReceiveListener.Context#getApplicationParam
   */
  @Deprecated
  <T> T getApplicationParam();
}
