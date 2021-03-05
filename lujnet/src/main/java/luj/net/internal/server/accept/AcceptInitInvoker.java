package luj.net.internal.server.accept;

import luj.net.api.server.ConnectionAcceptInitializer;

public class AcceptInitInvoker {

  public AcceptInitInvoker(ConnectionAcceptInitializer initializer, String bindHost,
      int bindPort, Object bindParam) {
    _initializer = initializer;
    _bindHost = bindHost;
    _bindPort = bindPort;
    _bindParam = bindParam;
  }

  public Object invoke() {
    ContextImpl ctx = new ContextImpl();
    ctx._bindParam = _bindParam;

    AddressImpl bindAddr = new AddressImpl();
    ctx._bindAddr = bindAddr;

    bindAddr._host = _bindHost;
    bindAddr._port = _bindPort;

    return _initializer.init(ctx);
  }

  private final ConnectionAcceptInitializer _initializer;

  private final String _bindHost;
  private final int _bindPort;

  private final Object _bindParam;
}
