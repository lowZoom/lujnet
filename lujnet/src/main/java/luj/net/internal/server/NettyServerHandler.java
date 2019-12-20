package luj.net.internal.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.data.NetReceiveListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class NettyServerHandler extends ChannelInboundHandlerAdapter {

  NettyServerHandler(Object applicationParam, NetReceiveListener receiveListener) {
    _applicationParam = applicationParam;
    _receiveListener = receiveListener;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ConnectionImpl conn = new ConnectionImpl(ctx.channel());
    ReceiveContextImpl receiveCtx = new ReceiveContextImpl((ByteBuf) msg, _applicationParam, conn);

    _receiveListener.onReceive(receiveCtx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);

  private final Object _applicationParam;
  private final NetReceiveListener _receiveListener;
}
