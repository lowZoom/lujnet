package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.data.NetReceiveListener;

final class ReceiveContextImpl implements NetReceiveListener.Context {

  ReceiveContextImpl(ByteBuf data) {
    _data = data;
  }

  @Override
  public ByteBuf data() {
    return _data;
  }

  private final ByteBuf _data;
}
