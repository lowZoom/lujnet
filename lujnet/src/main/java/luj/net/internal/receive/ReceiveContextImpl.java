package luj.net.internal.receive;

import io.netty.buffer.ByteBuf;
import luj.net.api.data.NetReceiveListener;

final class ReceiveContextImpl implements NetReceiveListener.Context {

  ReceiveContextImpl(ByteBuf data, Object appParam) {
    _data = data;
    _appParam = appParam;
  }

  @Override
  public ByteBuf getData() {
    return _data;
  }

  @Override
  public <T> T getApplicationParam() {
    return (T) _appParam;
  }

  private final ByteBuf _data;

  private final Object _appParam;
}
