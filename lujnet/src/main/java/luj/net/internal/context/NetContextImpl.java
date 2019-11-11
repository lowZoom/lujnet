package luj.net.internal.context;

import io.netty.channel.nio.NioEventLoopGroup;
import luj.net.api.NetConnection;
import luj.net.api.NetContext;
import luj.net.internal.connection.NetConnFactory;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot, NioEventLoopGroup workGroup) {
    _injectRoot = injectRoot;
    _workGroup = workGroup;
  }

  @Override
  public NetConnection createConnection(String host, int port, Object attachment) {
    return new NetConnFactory(host, port, _workGroup,
        _injectRoot.getReceiveListener(), attachment).create();
  }

  private final InjectRoot _injectRoot;

  private final NioEventLoopGroup _workGroup;
}
