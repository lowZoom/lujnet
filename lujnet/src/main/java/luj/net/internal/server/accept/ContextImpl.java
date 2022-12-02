package luj.net.internal.server.accept;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ServerAcceptInit;

final class ContextImpl implements ServerAcceptInit.Context {

  @Override
  public SocketChannel getChannel() {
    return _channel;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBindParam() {
    return (T) _bindParam;
  }

  SocketChannel _channel;

  Object _bindParam;
}
