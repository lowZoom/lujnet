package luj.net.internal.context;

import java.util.List;
import luj.ava.spring.Internal;
import luj.net.api.connection.NetDisconnectListener;
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

  public NetDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

  @Autowired(required = false)
  private ConnectionAcceptInitializer _acceptInitializer;

  @Autowired(required = false)
  private List<FrameDataReceiver> _frameReceivers;

  @Deprecated //FIXME: 建立连接的时候单独传
  @Autowired(required = false)
  private NetDisconnectListener _disconnectListener;
}
