package luj.net.internal.context;

import io.netty.channel.nio.NioEventLoopGroup;
import java.util.function.Consumer;
import luj.net.api.NetContext;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.api.server.NetServer;
import luj.net.internal.client.connect.NetClientConnectorV2;
import luj.net.internal.client.factory.ConnFactoryImpl;
import luj.net.internal.server.NetServerFactory;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot, NioEventLoopGroup workGroup) {
    _injectRoot = injectRoot;
    _workGroup = workGroup;
  }

  @Override
  public NetConnection createConnection(Consumer<NetConnection.Config> config) {
    return new NetClientConnectorV2(config, _workGroup).connect();
  }

  @Override
  public ConnectionFactory createConnectionFactory() {
    return new ConnFactoryImpl(new NioEventLoopGroup());
  }

  @Override
  public NetServer createServer() {
    return new NetServerFactory(_injectRoot.getAcceptInitializer(),
        _injectRoot.getFrameReceivers(), _injectRoot.getDisconnectListener()).create();
  }

  private final InjectRoot _injectRoot;

  private final NioEventLoopGroup _workGroup;
}
