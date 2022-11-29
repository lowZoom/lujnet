package luj.net.internal.context;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.api.server.ServerAcceptInit;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot {

  public ServerAcceptInit getAcceptInitializer() {
    return _acceptInitializer;
  }

  public List<FrameDataReceiver> getFrameReceivers() {
    return _frameReceivers;
  }

  public NetDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

  @Autowired(required = false)
  private ServerAcceptInit _acceptInitializer;

  @Autowired(required = false)
  List<FrameDataReceiver> _frameReceivers = ImmutableList.of();

  @Deprecated //FIXME: 建立连接的时候单独传
  @Autowired(required = false)
  private NetDisconnectListener _disconnectListener;
}
