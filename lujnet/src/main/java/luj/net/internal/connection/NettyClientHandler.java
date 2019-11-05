package luj.net.internal.connection;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.data.NetReceiveListener;
import luj.net.internal.receive.NetConnReceiver;

final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  NettyClientHandler(NetReceiveListener receiveListener) {
    _receiveListener = receiveListener;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    new NetConnReceiver((ByteBuf) msg, _receiveListener).receive();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
  }

  private final NetReceiveListener _receiveListener;
}
