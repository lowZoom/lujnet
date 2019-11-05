package luj.net.internal.context;

import luj.ava.spring.Internal;
import luj.net.api.data.NetReceiveListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot {

  public NetReceiveListener getReceiveListener() {
    return _receiveListener;
  }

  @Autowired
  private NetReceiveListener _receiveListener;
}
