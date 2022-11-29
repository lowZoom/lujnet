package luj.net.internal.client.factory;

import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import java.util.function.Consumer;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.internal.client.connect3.NetClientConnectorV3;

public class ConnFactoryImpl implements ConnectionFactory {

  public ConnFactoryImpl(NioEventLoopGroup workGroup) {
    _workGroup = workGroup;
  }

  @Override
  public ChannelFuture connect(Consumer<NetConnection.Config> config) {
    return new NetClientConnectorV3(config, _workGroup).connect();
  }

  private final NioEventLoopGroup _workGroup;
}
