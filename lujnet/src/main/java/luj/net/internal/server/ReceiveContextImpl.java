package luj.net.internal.server;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;
import luj.net.api.data.NetReceiveListener;

final class ReceiveContextImpl implements NetReceiveListener.Context {

  ReceiveContextImpl(ByteBuf data, Object appParam, NetConnection connection) {
    _data = data;
    _appParam = appParam;
    _connection = connection;
  }

  @Override
  public ByteBuf getData() {
    return _data;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationParam() {
    return (T) _appParam;
  }

  @Override
  public NetConnection getConnection() {
    return _connection;
  }

  private final ByteBuf _data;
  private final Object _appParam;

  private final NetConnection _connection;
}
