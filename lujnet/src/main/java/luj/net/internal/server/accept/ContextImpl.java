package luj.net.internal.server.accept;

import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.ConnectionAcceptInitializer.Connection;

final class ContextImpl implements ConnectionAcceptInitializer.Context {

  @Override
  public Connection getConnection() {
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBindParam() {
    return (T) _bindParam;
  }

  Object _bindParam;
}
