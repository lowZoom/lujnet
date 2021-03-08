package luj.net.internal.context;

import io.netty.channel.nio.NioEventLoopGroup;
import java.util.function.Consumer;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;
import luj.net.api.server.NetServer;
import luj.net.internal.client.NetClientConnector;
import luj.net.internal.client.connect.NetClientConnectorV2;
import luj.net.internal.server.NetServerFactory;
import luj.net.internal.server.NetServerStarter;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot, NioEventLoopGroup workGroup) {
    _injectRoot = injectRoot;
    _workGroup = workGroup;
  }

  @Override
  public NetConnection createConnection(String host, int port, Object param) {
    return new NetClientConnector(host, port, _workGroup, _injectRoot.getReceiveListener(),
        _injectRoot.getDisconnectListener(), param).connect();
  }

  @Override
  public NetConnection createConnection(Consumer<NetConnection.Config> config) {
    return new NetClientConnectorV2(config, _workGroup).connect();
  }

  @Override
  public void createServer(String host, int port, Object param) {
    new NetServerStarter(host, port, param, _injectRoot.getReceiveListener()).start();
  }

  @Override
  public NetServer createServer() {
    return new NetServerFactory(_injectRoot.getAcceptInitializer(),
        _injectRoot.getFrameReceivers()).create();
  }

  private final InjectRoot _injectRoot;

  private final NioEventLoopGroup _workGroup;
}
