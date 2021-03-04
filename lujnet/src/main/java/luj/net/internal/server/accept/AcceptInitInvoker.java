package luj.net.internal.server.accept;

import luj.net.api.server.ConnectionAcceptInitializer;

public class AcceptInitInvoker {

  public AcceptInitInvoker(ConnectionAcceptInitializer initializer, Object bindParam) {
    _initializer = initializer;
    _bindParam = bindParam;
  }

  public Object invoke() {
    ContextImpl ctx = new ContextImpl();
    ctx._bindParam = _bindParam;

    return _initializer.init(ctx);
  }

  private final ConnectionAcceptInitializer _initializer;

  private final Object _bindParam;
}
