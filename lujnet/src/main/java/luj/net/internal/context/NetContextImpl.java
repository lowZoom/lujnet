package luj.net.internal.context;

import luj.net.api.NetConnection;
import luj.net.api.NetContext;
import luj.net.internal.connection.NetConnFactory;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot) {
    _injectRoot = injectRoot;
  }

  @Override
  public NetConnection createConnection(String host, int port, Object attachment) {
    return new NetConnFactory(host, port, _injectRoot.getReceiveListener(), attachment).create();
  }

  private final InjectRoot _injectRoot;
}
