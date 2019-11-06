package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.NetConnection;
import luj.net.api.data.NetReceiveListener;

final class ReceiveContextImpl implements NetReceiveListener.Context {

  ReceiveContextImpl(ByteBuf data, NetConnection connection, Object appParam) {
    _data = data;
    _connection = connection;
    _appParam = appParam;
  }

  @Override
  public ByteBuf getData() {
    return _data;
  }

  @Override
  public NetConnection getConnection() {
    return _connection;
  }

  @Override
  public <T> T getApplicationParam() {
    return (T) _appParam;
  }

  private final ByteBuf _data;
  private final NetConnection _connection;

  private final Object _appParam;
}
