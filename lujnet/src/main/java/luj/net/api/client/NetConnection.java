package luj.net.api.client;

import io.netty.buffer.ByteBuf;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.FrameDataReceiver;

public interface NetConnection {

  interface Config {

    Config host(String host);

    Config port(int port);

    Config connectTimeoutMillis(int timeoutMs);

    @Deprecated
    Config receiveListener(NetReceiveListener listener);

    Config frameReceiver(FrameDataReceiver receiver);

    Config disconnectListener(NetDisconnectListener listener);
  }

  void send(byte[] data);

  void send(ByteBuf data);

  void close();

  boolean isClosed();

  /**
   * @see NetReceiveListener.Context#getApplicationParam
   */
  @Deprecated
  <T> T getApplicationParam();
}
