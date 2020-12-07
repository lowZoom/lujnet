package luj.net.internal.client.connect;

import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetReceiveListener;

final class ConfigImpl implements NetConnection.Config {

  @Override
  public NetConnection.Config host(String host) {
    _host = host;
    return this;
  }

  @Override
  public NetConnection.Config port(int port) {
    _port = port;
    return this;
  }

  @Override
  public NetConnection.Config connectTimeoutMillis(int timeoutMs) {
    _connectTimeout = timeoutMs;
    return this;
  }

  @Override
  public NetConnection.Config receiveListener(NetReceiveListener listener) {
    _receiver = listener;
    return this;
  }

  String _host;
  int _port;

  int _connectTimeout;
  NetReceiveListener _receiver;
}