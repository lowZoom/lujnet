package luj.net.internal.receive;

import static com.google.common.base.Preconditions.checkState;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetReceiveListener;

/**
 * @see luj.net.internal.receive.frame.FrameReceiveInvoker
 */
@Deprecated
public class NetConnReceiver {

  public NetConnReceiver(ByteBuf data, NetConnection connection,
      NetReceiveListener receiveListener) {
    _data = data;
    _connection = connection;
    _receiveListener = receiveListener;
  }

  public void receive() {
    checkState(!_connection.isClosed());

    ContextImpl ctx = new ContextImpl();
    ctx._data = _data;
    ctx._connection = _connection;

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
