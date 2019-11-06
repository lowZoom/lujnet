package luj.net.internal.context;

import luj.net.api.NetConnection;
import luj.net.api.NetContext;
import luj.net.internal.connection.NetConnFactory;

final class NetContextImpl implements NetContext {

  NetContextImpl(InjectRoot injectRoot, Object appParam) {
    _injectRoot = injectRoot;
    _appParam = appParam;
  }

  @Override
  public NetConnection createConnection(String host, int port) {
    return new NetConnFactory(host, port, _appParam, _injectRoot.getReceiveListener()).create();
  }

  private final InjectRoot _injectRoot;

  private final Object _appParam;
}
