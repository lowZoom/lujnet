package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetReceiveListener;

final class ContextImpl implements NetReceiveListener.Context {

  @Override
  public ByteBuf getData() {
    return _data;
  }

  @Override
  public <T> T getApplicationParam() {
    return _connection.getApplicationParam();
  }

  @Override
  public NetConnection getConnection() {
    return _connection;
  }

  ByteBuf _data;

  NetConnection _connection;
}
