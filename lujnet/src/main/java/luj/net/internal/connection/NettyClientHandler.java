package luj.net.internal.connection;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.NetConnection;
import luj.net.api.data.NetReceiveListener;
import luj.net.internal.receive.NetConnReceiver;

final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  NettyClientHandler(NetReceiveListener receiveListener) {
    _receiveListener = receiveListener;
  }

  public void setLujnetConn(NetConnection lujnetConn) {
    _lujnetConn = lujnetConn;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    new NetConnReceiver((ByteBuf) msg, _lujnetConn, _receiveListener).receive();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
  }

  private NetConnection _lujnetConn;

  private final NetReceiveListener _receiveListener;
}
