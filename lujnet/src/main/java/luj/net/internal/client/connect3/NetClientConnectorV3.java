package luj.net.internal.client.connect3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import java.util.function.Consumer;
import luj.net.api.client.NetConnection;
import luj.net.internal.client.connect.ClientBootMaker;
import luj.net.internal.client.connect3.init.ConnectInitInvoker;

public class NetClientConnectorV3 {

  public NetClientConnectorV3(Consumer<NetConnection.Config> configFiller,
      NioEventLoopGroup workGroup) {
    _configFiller = configFiller;
    _workGroup = workGroup;
  }

  public ChannelFuture connect() {
    var conf = new ConfigImpl();
    _configFiller.accept(conf);

    Bootstrap bootstrap = new ClientBootMaker(_workGroup).make();
    if (conf._connectTimeout > 0) {
      bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, conf._connectTimeout);
    }

    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        new ConnectInitInvoker(conf._listener._init, ch).invoke();
      }
    });

    return bootstrap.connect(conf._host, conf._port);
  }

  private final Consumer<NetConnection.Config> _configFiller;

  private final NioEventLoopGroup _workGroup;
}
