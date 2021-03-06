package luj.net.internal.receive.init;

import luj.net.api.server.FrameDataReceiver;

public class FrameReceiveState {

  public FrameReceiveState(ReceiveCumulateState cumulateState) {
    _cumulateState = cumulateState;
  }

  public int getByteCountToWait() {
    return _byteCountToWait;
  }

  public void setByteCountToWait(int byteCountToWait) {
    _byteCountToWait = byteCountToWait;
  }

  public FrameDataReceiver getNextReceiver() {
    return _nextReceiver;
  }

  public void setNextReceiver(FrameDataReceiver nextReceiver) {
    _nextReceiver = nextReceiver;
  }

  public ReceiveCumulateState getCumulateState() {
    return _cumulateState;
  }

  private int _byteCountToWait;
  private FrameDataReceiver _nextReceiver;

  private final ReceiveCumulateState _cumulateState;
}
