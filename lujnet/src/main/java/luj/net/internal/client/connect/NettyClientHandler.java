package luj.net.internal.client.connect;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.disconnect.NetDisconnInvoker;
import luj.net.internal.receive.init.FrameReceiveState;
import luj.net.internal.receive.read.ReceiveChannelReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ReceiveChannelReader.GET.read(ctx, (ByteBuf) msg, _receiveState, null);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    LOG.debug("连接断开：{}", ctx.channel());
    new NetDisconnInvoker(_disconnectListener, _lujnetConn).invoke();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);

  FrameReceiveState _receiveState;
  NetConnection _lujnetConn;

  FrameDataReceiver _frameReceiver;
  NetDisconnectListener _disconnectListener;
}
