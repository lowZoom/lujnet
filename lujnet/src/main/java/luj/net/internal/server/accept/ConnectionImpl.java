package luj.net.internal.server.accept;

import io.netty.buffer.ByteBuf;
import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ConnectionAcceptInitializer;

final class ConnectionImpl implements ConnectionAcceptInitializer.Connection {

  @Override
  public void write(ByteBuf data) {
    _channel.write(data);
  }

  @Override
  public void flush() {
    _channel.flush();
  }

  @Override
  public void close() {
    _channel.close();
  }

  SocketChannel _channel;
}
