package luj.net.internal.server.accept;

import java.net.InetSocketAddress;
import luj.net.api.server.ServerAcceptInit;

final class AddressImpl implements ServerAcceptInit.Address {

  static AddressImpl from(InetSocketAddress addr) {
    AddressImpl result = new AddressImpl();
    result._host = addr.getHostString();
    result._port = addr.getPort();
    return result;
  }

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
