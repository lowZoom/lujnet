package luj.net.internal.server.bind;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.internal.disconnect.NetDisconnectInvokerV2;
import luj.net.internal.receive.frame.FrameReceiveInvoker;
import luj.net.internal.receive.init.FrameReceiveState;
import luj.net.internal.receive.read.ReceiveChannelReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
final class NettyServerHandlerV2 extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    // 先触发第一个接收器
    FrameReceiveInvoker.GET.invoke(null, _receiveState, _connState);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ReceiveChannelReader.GET.read(ctx, (ByteBuf) msg, _receiveState, _connState);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    NetDisconnectInvokerV2.GET.invoke(_disconnectListener, _connState);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandlerV2.class);

  FrameReceiveState _receiveState;
  NetDisconnectListener _disconnectListener;

  Object _connState;
}
