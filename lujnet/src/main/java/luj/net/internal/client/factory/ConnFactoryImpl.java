package luj.net.internal.client.factory;

import io.netty.channel.nio.NioEventLoopGroup;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.internal.client.connect.NetClientConnectorV2;
import luj.net.internal.client.connect3.NetClientConnectorV3;

import java.util.function.Consumer;

public class ConnFactoryImpl implements ConnectionFactory {

  public ConnFactoryImpl(NioEventLoopGroup workGroup) {
    _workGroup = workGroup;
  }

  @Override
  public ConnectFuture connect(Consumer<NetConnection.Config> config) {
    return new NetClientConnectorV3(config, _workGroup).connect();
  }

  @Override
  public NetConnection createConnection(Consumer<NetConnection.Config> config) {
    return new NetClientConnectorV2(config, _workGroup).connect();
  }

  private final NioEventLoopGroup _workGroup;
}
