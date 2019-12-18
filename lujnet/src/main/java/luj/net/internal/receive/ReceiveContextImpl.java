package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;
import luj.net.api.data.NetReceiveListener;

final class ReceiveContextImpl implements NetReceiveListener.Context {

  ReceiveContextImpl(ByteBuf data, NetConnection connection) {
    _data = data;
    _connection = connection;
  }

  @Override
  public ByteBuf getData() {
    return _data;
  }

  @Override
  public NetConnection getConnection() {
    return _connection;
  }

  private final ByteBuf _data;

  private final NetConnection _connection;
}
