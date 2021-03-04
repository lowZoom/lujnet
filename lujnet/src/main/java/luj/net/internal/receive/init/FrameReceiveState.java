package luj.net.internal.receive.init;

import io.netty.buffer.ByteBuf;
import java.util.Map;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.FrameDataReceiver;

public class FrameReceiveState {

  public FrameReceiveState(ByteBuf buffer, ReceiveCumulateState cumulateState,
      Map<Class<?>, FrameDataReceiver> receiverMap, NetReceiveListener receiveListener) {
    _buffer = buffer;
    _cumulateState = cumulateState;
    _receiverMap = receiverMap;
    _receiveListener = receiveListener;
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

  public ByteBuf getBuffer() {
    return _buffer;
  }

  public ReceiveCumulateState getCumulateState() {
    return _cumulateState;
  }

  public Map<Class<?>, FrameDataReceiver> getReceiverMap() {
    return _receiverMap;
  }

  public NetReceiveListener getReceiveListener() {
    return _receiveListener;
  }

  private int _byteCountToWait;
  private FrameDataReceiver _nextReceiver;

  @Deprecated
  private final ByteBuf _buffer;
  private final ReceiveCumulateState _cumulateState;

  private final Map<Class<?>, FrameDataReceiver> _receiverMap;
  private final NetReceiveListener _receiveListener;
}
