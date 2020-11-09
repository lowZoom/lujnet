package luj.net.internal.receive.frame;

import luj.net.api.server.FrameDataReceiver;
import luj.net.api.server.FrameDataReceiver.Result;

final class RecvContextImpl implements FrameDataReceiver.Context {

  @Override
  public <T> T getLastFrame() {
    return null;
  }

  @Override
  public Result finish() {
    return _result;
  }

  RecvResultImpl _result;
}
