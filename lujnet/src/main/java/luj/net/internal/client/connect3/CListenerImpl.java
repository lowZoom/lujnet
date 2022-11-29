package luj.net.internal.client.connect3;

import luj.net.api.client.ClientConnectInit;
import luj.net.api.client.NetConnection;

final class CListenerImpl implements NetConnection.Listener {

  @Override
  public NetConnection.Listener init(ClientConnectInit val) {
    _init = val;
    return this;
  }

  ClientConnectInit _init;
}
