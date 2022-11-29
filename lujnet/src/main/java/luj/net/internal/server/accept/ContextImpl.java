package luj.net.internal.server.accept;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ServerAcceptInit;

final class ContextImpl implements ServerAcceptInit.Context {

  @Override
  public ServerAcceptInit.Connection getConnection() {
    return _conn;
  }

  @Override
  public ServerAcceptInit.Address getBindAddress() {
    return _bindAddr;
  }

  @Override
  public SocketChannel getChannel() {
    return _channel;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBindParam() {
    return (T) _bindParam;
  }

  @Deprecated
  ConnectionImpl _conn;
  @Deprecated
  AddressImpl _bindAddr;

  SocketChannel _channel;
  Object _bindParam;
}
