package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.data.NetReceiveListener;

public class NetConnReceiver {

  public NetConnReceiver(ByteBuf data, Object appParam, NetReceiveListener receiveListener) {
    _data = data;
    _appParam = appParam;
    _receiveListener = receiveListener;
  }

  public void receive() {
    ReceiveContextImpl ctx = new ReceiveContextImpl(_data, _appParam);
    try {
      _receiveListener.onReceive(ctx);

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final ByteBuf _data;

  private final Object _appParam;
  private final NetReceiveListener _receiveListener;
}
