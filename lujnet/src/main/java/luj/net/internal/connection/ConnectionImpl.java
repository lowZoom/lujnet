package luj.net.internal.connection;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import luj.net.api.NetConnection;

final class ConnectionImpl implements NetConnection {

  ConnectionImpl(Channel channel) {
    _channel = channel;
  }

  @Override
  public void send(byte[] data) {
    _channel.writeAndFlush(Unpooled.copiedBuffer(data));
  }

  private final Channel _channel;
}
