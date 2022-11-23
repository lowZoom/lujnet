package luj.net.internal.connection;

import io.netty.channel.Channel;
import luj.net.api.client.NetConnection;

public class NetConnFactory {

  public NetConnFactory(Channel channel) {
    _channel = channel;
  }

  public NetConnection create() {
    var conn = new ConnectionImpl();
    conn._channel = _channel;

    return conn;
  }

  private final Channel _channel;
}
