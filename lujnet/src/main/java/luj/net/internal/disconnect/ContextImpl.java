package luj.net.internal.disconnect;

import luj.net.api.connection.NetDisconnectListener;

final class ContextImpl implements NetDisconnectListener.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getConnectionState() {
    return (T) _connState;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationParam() {
    return (T) _param;
  }

  Object _connState;

  Object _param;
}
