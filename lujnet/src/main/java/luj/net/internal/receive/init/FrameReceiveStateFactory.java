package luj.net.internal.receive.init;

import static com.google.common.base.Preconditions.checkState;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.FrameDataReceiver;

public class FrameReceiveStateFactory {

  public FrameReceiveStateFactory(List<FrameDataReceiver> receiverList,
      NetReceiveListener receiveListener) {
    _receiverList = receiverList;
    _receiveListener = receiveListener;
  }

  public FrameReceiveState create() {
    Map<Class<?>, FrameDataReceiver> receiverMap = _receiverList.stream()
        .collect(Collectors.toMap(FrameDataReceiver::getClass, Function.identity()));

    ByteBuf buffer = Unpooled.buffer();
    FrameReceiveState state = new FrameReceiveState(buffer, receiverMap, _receiveListener);

    state.setNextReceiver(getHeadReceiver());
    return state;
  }

  private Class<?> getHeadReceiver() {
    List<Class<?>> headList = _receiverList.stream()
        .map(FrameDataReceiver::getClass)
        .filter(this::isHead)
        .collect(Collectors.toList());

    int headCount = headList.size();
    checkState(headCount == 1, headCount);

    return headList.get(0);
  }

  private boolean isHead(Class<?> receiverType) {
    return receiverType.isAnnotationPresent(FrameDataReceiver.Head.class);
  }

  private final List<FrameDataReceiver> _receiverList;

  private final NetReceiveListener _receiveListener;
}
