package luj.net.internal.server.accept;

import luj.net.api.server.ConnectionAcceptInitializer;

final class ContextImpl implements ConnectionAcceptInitializer.Context {

  @Override
  public ConnectionAcceptInitializer.Address getBindAddress() {
    return _bindAddr;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBindParam() {
    return (T) _bindParam;
  }

  AddressImpl _bindAddr;

  Object _bindParam;
}
