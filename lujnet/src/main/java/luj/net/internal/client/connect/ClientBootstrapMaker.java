package luj.net.internal.client.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientBootstrapMaker {

  public ClientBootstrapMaker(EventLoopGroup workGroup) {
    _workGroup = workGroup;
  }

  public Bootstrap make() {
    Bootstrap result = new Bootstrap();
    result.channel(NioSocketChannel.class);
    result.group(_workGroup);

    return result
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true);
  }

  private final EventLoopGroup _workGroup;
}
