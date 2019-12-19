package luj.net.internal.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import luj.net.api.client.NetConnection;

final class ConnectionImpl implements NetConnection {

  ConnectionImpl(Channel channel) {
    _channel = channel;
  }

  @Override
  public void send(byte[] data) {
    _channel.writeAndFlush(Unpooled.copiedBuffer(data));
  }

  @Override
  public void close() {
    _channel.close();
  }

  @Override
  public <T> T getApplicationParam() {
    return null;
  }

  private final Channel _channel;
}
