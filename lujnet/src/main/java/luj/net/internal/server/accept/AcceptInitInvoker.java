package luj.net.internal.server.accept;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ConnectionAcceptInitializer;

public class AcceptInitInvoker {

  public AcceptInitInvoker(ConnectionAcceptInitializer initializer, SocketChannel channel,
      String bindHost, int bindPort, Object bindParam) {
    _initializer = initializer;
    _channel = channel;
    _bindHost = bindHost;
    _bindPort = bindPort;
    _bindParam = bindParam;
  }

  public Object invoke() throws Exception {
    ContextImpl ctx = new ContextImpl();
    ctx._bindParam = _bindParam;

    ConnectionImpl conn = new ConnectionImpl();
    ctx._conn = conn;
    conn._channel = _channel;

    AddressImpl bindAddr = new AddressImpl();
    ctx._bindAddr = bindAddr;
    bindAddr._host = _bindHost;
    bindAddr._port = _bindPort;

    return _initializer.init(ctx);
  }

  private final ConnectionAcceptInitializer _initializer;
  private final SocketChannel _channel;

  private final String _bindHost;
  private final int _bindPort;

  private final Object _bindParam;
}
