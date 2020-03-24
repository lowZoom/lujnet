package luj.net.internal.context;

import io.netty.channel.nio.NioEventLoopGroup;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;
import luj.net.internal.client.NetClientConnector;
import luj.net.internal.server.NetServerStarter;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot, NioEventLoopGroup workGroup) {
    _injectRoot = injectRoot;
    _workGroup = workGroup;
  }

  @Override
  public NetConnection createConnection(String host, int port, Object param) {
    return new NetClientConnector(host, port, _workGroup,
        _injectRoot.getReceiveListener(), param).connect();
  }

  @Override
  public void createServer(String host, int port, Object param) {
    new NetServerStarter(host, port, param, _injectRoot.getReceiveListener()).start();
  }

  private final InjectRoot _injectRoot;

  private final NioEventLoopGroup _workGroup;
}
