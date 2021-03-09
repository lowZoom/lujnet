package luj.net.internal.connection;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
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
    send(Unpooled.wrappedBuffer(data));
  }

  @Override
  public void send(ByteBuf data) {
    checkState(!isClosed());
    _channel.writeAndFlush(data);
  }

  @Override
  public void close() {
    if (isClosed()) {
      return;
    }
    _channel.close();
  }

  @Override
  public boolean isClosed() {
    return _channel == null || !_channel.isActive();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationParam() {
    return (T) _connParam;
  }

  private final Channel _channel;

  private final Object _connParam;
}
