package luj.net.internal.server.accept;

import luj.net.api.server.ConnectionAcceptInitializer;

final class AddressImpl implements ConnectionAcceptInitializer.Address {

  @Override
  public String host() {
    return _host;
  }

  @Override
  public int port() {
    return _port;
  }

  String _host;

  int _port;
}
