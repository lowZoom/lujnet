package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;
import luj.net.api.data.NetReceiveListener;

public class NetConnReceiver {

  public NetConnReceiver(ByteBuf data, NetConnection connection,
      NetReceiveListener receiveListener) {
    _data = data;
    _connection = connection;
    _receiveListener = receiveListener;
  }

  public void receive() {
    ReceiveContextImpl ctx = new ReceiveContextImpl(_data, _connection);
    try {
      _receiveListener.onReceive(ctx);

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);

    } finally {
      _data.release();
    }
  }

  private final ByteBuf _data;
  private final NetConnection _connection;

  private final NetReceiveListener _receiveListener;
}
