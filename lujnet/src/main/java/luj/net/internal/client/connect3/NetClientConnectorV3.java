package luj.net.internal.client.connect3;

import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.internal.client.connect.NetClientConnectorV2;

import java.util.function.Consumer;

public class NetClientConnectorV3 {

  public NetClientConnectorV3(Consumer<NetConnection.Config> configFiller, NioEventLoopGroup workGroup) {
    _configFiller = configFiller;
    _workGroup = workGroup;
  }

  public ConnectionFactory.ConnectFuture connect() {
    ChannelFuture connect = new NetClientConnectorV2(_configFiller, _workGroup).connect2();

    FutureImpl future = new FutureImpl();
    future._nettyFuture = connect;

    return future;
  }

  private final Consumer<NetConnection.Config> _configFiller;

  private final NioEventLoopGroup _workGroup;
}
