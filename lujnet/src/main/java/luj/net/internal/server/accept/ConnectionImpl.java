package luj.net.internal.server.accept;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.socket.SocketChannel;
import java.net.InetSocketAddress;
import luj.net.api.server.ServerAcceptInit;

final class ConnectionImpl implements ServerAcceptInit.Connection {

  @Override
  public ByteBufAllocator alloc() {
    return _channel.alloc();
  }

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

  @Override
  public ServerAcceptInit.Address getRemoteAddress() {
    InetSocketAddress addr = _channel.remoteAddress();
    return AddressImpl.from(addr);
  }

  @Override
  public String toString() {
    return _channel.toString();
  }

  SocketChannel _channel;
}
