package luj.net.internal.connection;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import luj.net.api.client.NetConnection;

final class ConnectionImpl implements NetConnection {

  ConnectionImpl(Channel channel, Object connParam) {
    _channel = channel;
    _connParam = connParam;
  }

  @Override
  public void send(byte[] data) {
    _channel.writeAndFlush(Unpooled.copiedBuffer(data));
  }

  @Override
  public void close() {
    try {
      _channel.close().sync();

    } catch (InterruptedException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Override
  public boolean isClosed() {
    return !_channel.isActive();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationParam() {
    return (T) _connParam;
  }

  private final Channel _channel;

  private final Object _connParam;
}
