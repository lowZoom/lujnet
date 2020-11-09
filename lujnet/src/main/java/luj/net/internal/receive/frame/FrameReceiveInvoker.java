package luj.net.internal.receive.frame;

import io.netty.buffer.ByteBuf;
import java.util.Map;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.receive.init.FrameReceiveState;

public class FrameReceiveInvoker {

  public FrameReceiveInvoker(FrameReceiveState receiveState, ByteBuf in) {
    _receiveState = receiveState;
    _in = in;
  }

  public void invoke() {
    ByteBuf buffer = _receiveState.getBuffer();
    buffer.writeBytes(_in);

    RecvContextImpl ctx = new RecvContextImpl();
    ctx._result = new RecvResultImpl();

    FrameDataReceiver nextReceiver = getNextReceiver();
    nextReceiver.receive(ctx);

    //TODO: 接收外部设置

//    NetConnection conn = new NetConnFactory(ctx.channel(), null).create();
//    ReceiveContextImpl receiveCtx = new ReceiveContextImpl(, _applicationParam, conn);
//    _receiveListener.onReceive(receiveCtx);
  }

  private FrameDataReceiver getNextReceiver() {
    Map<Class<?>, FrameDataReceiver> receiverMap = _receiveState.getReceiverMap();
    return receiverMap.get(_receiveState.getNextReceiver());
  }

  private final FrameReceiveState _receiveState;

  private final ByteBuf _in;
}
