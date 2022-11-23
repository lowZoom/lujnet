package luj.net.internal.connection;

import static com.google.common.base.Preconditions.checkState;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import luj.net.api.client.NetConnection;

final class ConnectionImpl implements NetConnection {

  @Override
  public void send(byte[] data) {
    send(_channel.alloc().buffer(data.length).writeBytes(data));
  }

  @Override
  public void send(ByteBuf data) {
    checkState(!isClosed());

//    int ref = data.refCnt();
//    checkState(ref == 1, ref);

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

  Channel _channel;
}
