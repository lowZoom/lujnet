package luj.net.internal.context;

import java.util.List;
import luj.ava.spring.Internal;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.FrameDataReceiver;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot {

  public ConnectionAcceptInitializer getAcceptInitializer() {
    return _acceptInitializer;
  }

  public List<FrameDataReceiver> getFrameReceivers() {
    return _frameReceivers;
  }

  public NetReceiveListener getReceiveListener() {
    return _receiveListener;
  }

  public NetDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

  @Autowired
  private ConnectionAcceptInitializer _acceptInitializer;

  @Autowired
  private List<FrameDataReceiver> _frameReceivers;

  //FIXME: 建立连接的时候单独传
  @Deprecated
  @Autowired(required = false)
  private NetReceiveListener _receiveListener;

  //FIXME: 建立连接的时候单独传
  @Deprecated
  @Autowired(required = false)
  private NetDisconnectListener _disconnectListener;
}
