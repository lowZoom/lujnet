package luj.net.internal.connection;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.data.NetReceiveListener;
import luj.net.internal.receive.NetConnReceiver;

final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  NettyClientHandler(Object appParam, NetReceiveListener receiveListener) {
    _appParam = appParam;
    _receiveListener = receiveListener;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    new NetConnReceiver((ByteBuf) msg, _appParam, _receiveListener).receive();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
  }

  private final Object _appParam;

  private final NetReceiveListener _receiveListener;
}
