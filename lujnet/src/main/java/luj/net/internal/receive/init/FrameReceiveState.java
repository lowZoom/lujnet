package luj.net.internal.receive.init;

import io.netty.buffer.ByteBuf;
import java.util.Map;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.FrameDataReceiver;

public class FrameReceiveState {

  public FrameReceiveState(ByteBuf buffer, Map<Class<?>, FrameDataReceiver> receiverMap,
      NetReceiveListener receiveListener) {
    _buffer = buffer;
    _receiverMap = receiverMap;
    _receiveListener = receiveListener;
  }

  public Class<?> getNextReceiver() {
    return _nextReceiver;
  }

  public void setNextReceiver(Class<?> nextReceiver) {
    _nextReceiver = nextReceiver;
  }

  public long getByteCountToWait() {
    return _byteCountToWait;
  }

  public void setByteCountToWait(long byteCountToWait) {
    _byteCountToWait = byteCountToWait;
  }

  public ByteBuf getBuffer() {
    return _buffer;
  }

  public Map<Class<?>, FrameDataReceiver> getReceiverMap() {
    return _receiverMap;
  }

  public NetReceiveListener getReceiveListener() {
    return _receiveListener;
  }

  private Class<?> _nextReceiver;
  private long _byteCountToWait;

  private final ByteBuf _buffer;
  private final Map<Class<?>, FrameDataReceiver> _receiverMap;

  private final NetReceiveListener _receiveListener;
}
