package luj.net.internal.client.connect3.init;

import io.netty.channel.socket.SocketChannel;
import luj.net.api.client.ClientConnectInit;

public class ConnectInitInvoker {

  public ConnectInitInvoker(ClientConnectInit init, SocketChannel channel) {
    _init = init;
    _channel = channel;
  }

  public void invoke() {
    var ctx = new ContextImpl();
    ctx._channel = _channel;

    _init.onInit(ctx);
  }

  private final ClientConnectInit _init;

  private final SocketChannel _channel;
}
