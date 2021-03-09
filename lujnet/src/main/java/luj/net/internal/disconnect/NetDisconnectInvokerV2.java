package luj.net.internal.disconnect;

import luj.net.api.connection.NetDisconnectListener;

public enum NetDisconnectInvokerV2 {
  GET;

  public void invoke(NetDisconnectListener listener, Object connState) {
    ContextImpl ctx = new ContextImpl();
    ctx._connState = connState;

    listener.onDisconnect(ctx);
  }
}
