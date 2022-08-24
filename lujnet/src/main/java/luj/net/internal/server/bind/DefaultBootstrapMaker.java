package luj.net.internal.server.bind;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DefaultBootstrapMaker {

  public DefaultBootstrapMaker(EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
    _bossGroup = bossGroup;
    _workerGroup = workerGroup;
  }

  public ServerBootstrap make() {
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.channel(NioServerSocketChannel.class);
    bootstrap.group(_bossGroup, _workerGroup);

    bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
    return bootstrap;
  }

  private final EventLoopGroup _bossGroup;
  private final EventLoopGroup _workerGroup;
}
