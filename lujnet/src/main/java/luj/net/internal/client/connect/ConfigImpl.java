package luj.net.internal.client.connect;

import java.util.function.Consumer;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;

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
  public NetConnection.Config listener(Consumer<NetConnection.Listener> val) {
    throw new UnsupportedOperationException("该类已废弃");
  }

  @Override
  public NetConnection.Config frameReceiver(FrameDataReceiver receiver) {
    _frameReceiver = receiver;
    return this;
  }

  @Override
  public NetConnection.Config disconnectListener(NetDisconnectListener listener) {
    _disconnectListener = listener;
    return this;
  }

  String _host;
  int _port;
  int _connectTimeout;

  FrameDataReceiver _frameReceiver;
  NetDisconnectListener _disconnectListener;
}
