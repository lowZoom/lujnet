package luj.net.internal.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.List;
import luj.net.api.connection.NetReceiveListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.receive.frame.FrameReceiveInvoker;
import luj.net.internal.receive.init.FrameReceiveState;
import luj.net.internal.receive.init.FrameReceiveStateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class NettyServerHandlerV2 extends ChannelInboundHandlerAdapter {

  static NettyServerHandlerV2 create(Object applicationParam, List<FrameDataReceiver> receiverList,
      NetReceiveListener receiveListener) {
    NettyServerHandlerV2 handler = new NettyServerHandlerV2();
    handler._applicationParam = applicationParam;

    handler._receiveState = new FrameReceiveStateFactory(receiverList, receiveListener).create();
    return handler;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    new FrameReceiveInvoker(_receiveState, (ByteBuf) msg).invoke();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandlerV2.class);

  private FrameReceiveState _receiveState;

  private Object _applicationParam;
}
