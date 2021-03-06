package luj.net.internal.receive.frame;

import io.netty.buffer.ByteBuf;
import luj.net.api.server.FrameDataReceiver;

final class RecvContextImpl implements FrameDataReceiver.Context {

  @Override
  public ByteBuf getLastFrame() {
    return _lastFrame;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getConnectionState() {
    return (T) _connectionState;
  }

  @Override
  public FrameDataReceiver.Result then() {
    return _result;
  }

  ByteBuf _lastFrame;
  Object _connectionState;

  RecvResultImpl _result;
}
