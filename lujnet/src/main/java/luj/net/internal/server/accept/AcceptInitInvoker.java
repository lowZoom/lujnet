package luj.net.internal.server.accept;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ServerAcceptInit;

/**
 * @see AcceptInitInvokerV2
 */
@Deprecated
public class AcceptInitInvoker {

  public AcceptInitInvoker(ServerAcceptInit initializer, SocketChannel channel,
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

    _initializer.onInit(ctx);
    return null;
  }

  private final ServerAcceptInit _initializer;
  private final SocketChannel _channel;

  private final String _bindHost;
  private final int _bindPort;

  private final Object _bindParam;
}
