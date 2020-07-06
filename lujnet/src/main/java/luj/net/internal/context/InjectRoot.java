package luj.net.internal.context;

import luj.ava.spring.Internal;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.connection.NetReceiveListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot {

  public NetReceiveListener getReceiveListener() {
    return _receiveListener;
  }

  public NetDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

  @Autowired
  private NetReceiveListener _receiveListener;

  @Autowired
  private NetDisconnectListener _disconnectListener;
}
