package luj.net.internal.receive.frame;

import luj.net.api.server.FrameDataReceiver;

final class RecvContextImpl implements FrameDataReceiver.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getLastFrame() {
    return (T) _lastFrame;
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

  Object _lastFrame;
  Object _connectionState;

  RecvResultImpl _result;
}
