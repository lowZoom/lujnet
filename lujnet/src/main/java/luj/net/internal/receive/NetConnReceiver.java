package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.data.NetReceiveListener;

public class NetConnReceiver {

  public NetConnReceiver(ByteBuf data, NetReceiveListener receiveListener) {
    _data = data;
    _receiveListener = receiveListener;
  }

  public void receive() {
    ReceiveContextImpl ctx = new ReceiveContextImpl(_data);
    _receiveListener.onReceive(ctx);
  }

  private final ByteBuf _data;

  private final NetReceiveListener _receiveListener;
}
