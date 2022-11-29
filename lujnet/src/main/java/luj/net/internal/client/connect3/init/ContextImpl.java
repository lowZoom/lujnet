package luj.net.internal.client.connect3.init;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.client.ClientConnectInit;

final class ContextImpl implements ClientConnectInit.Context {

  @Override
  public SocketChannel getChannel() {
    return _channel;
  }

  SocketChannel _channel;
}
