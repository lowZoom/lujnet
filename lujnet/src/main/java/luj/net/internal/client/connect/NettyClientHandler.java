package luj.net.internal.client.connect;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.disconnect.NetDisconnectInvokerV2;
import luj.net.internal.receive.frame.FrameReceiveInvoker;
import luj.net.internal.receive.init.FrameReceiveState;
import luj.net.internal.receive.read.ReceiveChannelReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    _active = true;

    // 先触发第一个接收器
    FrameReceiveInvoker.GET.invoke(null, _receiveState, null);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ReceiveChannelReader.GET.read(ctx, (ByteBuf) msg, _receiveState, null);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    if (!_active) {
      // 连上过才会触发断连
      return;
    }

    _active = false;
    NetDisconnectInvokerV2.GET.invoke(_disconnectListener, null);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);

  boolean _active;
  FrameReceiveState _receiveState;

  FrameDataReceiver _frameReceiver;
  NetDisconnectListener _disconnectListener;
}
