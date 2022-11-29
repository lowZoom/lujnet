package luj.net.internal.server.accept;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.server.ServerAcceptInit;

public class AcceptInitInvokerV2 {

  public AcceptInitInvokerV2(ServerAcceptInit initializer, SocketChannel channel,
      Object bindParam) {
    _initializer = initializer;
    _channel = channel;
    _bindParam = bindParam;
  }

  public void invoke() throws Exception {
    var ctx = new ContextImpl();
    ctx._channel = _channel;
    ctx._bindParam = _bindParam;

    _initializer.onInit(ctx);
  }

  private final ServerAcceptInit _initializer;

  private final SocketChannel _channel;
  private final Object _bindParam;
}
