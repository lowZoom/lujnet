package luj.net.internal.receive.frame;

import luj.net.api.server.FrameDataReceiver;

final class RecvResultImpl implements FrameDataReceiver.Result {

  @Override
  public FrameDataReceiver.Result waitBytes(int byteCount) {
    _byteCountToWait = byteCount;
    return this;
  }

  @Override
  public FrameDataReceiver.Result nextReceiver(Class<? extends FrameDataReceiver> receiver) {
    _nextReceiver = receiver;
    return this;
  }

  int _byteCountToWait;

  Class<?> _nextReceiver;
}
