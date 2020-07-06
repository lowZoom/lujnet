package luj.net.internal.disconnect;

import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;

public class NetDisconnInvoker {

  public NetDisconnInvoker(NetDisconnectListener disconnectListener, NetConnection conn) {
    _disconnectListener = disconnectListener;
    _conn = conn;
  }

  public void invoke() {
    ContextImpl ctx = new ContextImpl();
    ctx._param = _conn.getApplicationParam();

    _disconnectListener.onDisconnect(ctx);
  }

  private final NetDisconnectListener _disconnectListener;

  private final NetConnection _conn;
}
