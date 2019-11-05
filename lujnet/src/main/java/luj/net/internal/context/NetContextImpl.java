package luj.net.internal.context;

import luj.net.api.NetConnection;
import luj.net.api.NetContext;
import luj.net.internal.connection.NetConnFactory;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot) {
    _injectRoot = injectRoot;
  }

  @Override
  public NetConnection createConnection(String host, int port) {
    return new NetConnFactory(host, port, _injectRoot.getReceiveListener()).create();
  }

  private final InjectRoot _injectRoot;
}
