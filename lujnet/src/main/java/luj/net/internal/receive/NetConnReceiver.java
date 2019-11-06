package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.NetConnection;
import luj.net.api.data.NetReceiveListener;

public class NetConnReceiver {

  public NetConnReceiver(ByteBuf data, NetConnection connection, Object appParam,
      NetReceiveListener receiveListener) {
    _data = data;
    _connection = connection;
    _appParam = appParam;
    _receiveListener = receiveListener;
  }

  public void receive() {
    ReceiveContextImpl ctx = new ReceiveContextImpl(_data, _connection, _appParam);
    try {
      _receiveListener.onReceive(ctx);

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final ByteBuf _data;
  private final NetConnection _connection;

  private final Object _appParam;
  private final NetReceiveListener _receiveListener;
}
