package luj.net.internal.receive.frame;

import io.netty.buffer.ByteBuf;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.receive.init.FrameReceiveState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum FrameReceiveInvoker {
  GET;

  public void invoke(ByteBuf frameBuf, FrameReceiveState receiveState, Object connState)
      throws Exception {
    FrameDataReceiver curReceiver = receiveState.getNextReceiver();
    if (curReceiver == null) {
      int frameLen = (frameBuf == null) ? -1 : frameBuf.readableBytes();
      LOG.warn("未发现数据接收器，跳过：{}", frameLen);

      if (frameBuf != null) {
        frameBuf.readerIndex(frameBuf.readerIndex() + frameLen);
      }
      return;
    }

    RecvContextImpl ctx = new RecvContextImpl();
    ctx._lastFrame = frameBuf;
    ctx._connectionState = connState;

    RecvResultImpl result = new RecvResultImpl();
    ctx._result = result;

    curReceiver.receive(ctx);

    int waitBytes = result._byteCountToWait;
    FrameDataReceiver nextRecv = result._nextReceiver;

    LOG.debug("当前：{}，等：{}，下一个：{}", curReceiver.getClass().getSimpleName(),
        waitBytes, nextRecv.getClass().getSimpleName());

    receiveState.setByteCountToWait(waitBytes);
    receiveState.setNextReceiver(nextRecv);
  }

  private static final Logger LOG = LoggerFactory.getLogger(FrameReceiveInvoker.class);
}
