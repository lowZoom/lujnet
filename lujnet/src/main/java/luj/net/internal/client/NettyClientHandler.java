package luj.net.internal.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.connection.NetReceiveListener;
import luj.net.internal.disconnect.NetDisconnectInvoker;
import luj.net.internal.receive.NetConnReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
final class NettyClientHandler extends ChannelInboundHandlerAdapter {

  NettyClientHandler(NetReceiveListener receiveListener, NetDisconnectListener disconnectListener) {
    _receiveListener = receiveListener;
    _disconnectListener = disconnectListener;
  }

  public void setLujnetConn(NetConnection lujnetConn) {
    _lujnetConn = lujnetConn;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    new NetConnReceiver((ByteBuf) msg, _lujnetConn, _receiveListener).receive();
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    LOG.debug("连接断开：{}", ctx.channel());
    new NetDisconnectInvoker(_disconnectListener, _lujnetConn).invoke();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);

  private NetConnection _lujnetConn;

  private final NetReceiveListener _receiveListener;
  private final NetDisconnectListener _disconnectListener;
}
