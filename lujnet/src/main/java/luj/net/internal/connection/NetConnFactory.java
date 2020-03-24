package luj.net.internal.connection;

import io.netty.channel.Channel;
import luj.net.api.client.NetConnection;

public class NetConnFactory {

  public NetConnFactory(Channel channel, Object connParam) {
    _channel = channel;
    _connParam = connParam;
  }

  public NetConnection create() {
    return new ConnectionImpl(_channel, _connParam);
  }

  private final Channel _channel;

  private final Object _connParam;
}
