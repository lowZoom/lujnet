package luj.net.internal.receive.init;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.stream.Collectors;
import luj.net.api.server.FrameDataReceiver;

public class FrameReceiveStateFactory {

  public FrameReceiveStateFactory(List<FrameDataReceiver> receiverList) {
    _receiverList = receiverList;
  }

  public FrameReceiveState create() {
    ReceiveCumulateState cumulateState = new ReceiveCumulateState();
    FrameReceiveState state = new FrameReceiveState(cumulateState);

    state.setNextReceiver(getHeadReceiver());
    return state;
  }

  private FrameDataReceiver getHeadReceiver() {
    if (_receiverList.isEmpty()) {
      return null;
    }
    if (_receiverList.size() == 1) {
      return _receiverList.get(0);
    }

    List<FrameDataReceiver> headList = _receiverList.stream()
        .filter(r -> isHead(r.getClass()))
        .collect(Collectors.toList());

    int headCount = headList.size();
    checkState(headCount <= 1, headCount);

    return headCount == 0 ? null : headList.get(0);
  }

  private boolean isHead(Class<?> receiverType) {
    return receiverType.isAnnotationPresent(FrameDataReceiver.Head.class);
  }

  private final List<FrameDataReceiver> _receiverList;
}
