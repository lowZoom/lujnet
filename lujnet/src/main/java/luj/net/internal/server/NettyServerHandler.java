package luj.net.internal.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetReceiveListener;
import luj.net.internal.connection.NetConnFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 已弃用，有泄漏BUG
 *
 * @see luj.net.internal.server.bind.NettyServerHandlerV2
 */
@Deprecated
final class NettyServerHandler extends ChannelInboundHandlerAdapter {

  NettyServerHandler(Object applicationParam, NetReceiveListener receiveListener) {
    _applicationParam = applicationParam;
    _receiveListener = receiveListener;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    NetConnection conn = new NetConnFactory(ctx.channel(), null).create();
    ByteBuf buf = (ByteBuf) msg;

//    try {
    ReceiveContextImpl receiveCtx = new ReceiveContextImpl(buf, _applicationParam, conn);
    _receiveListener.onReceive(receiveCtx);
//
//    } finally {
//      ReferenceCountUtil.release(buf);
//    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);

  private final Object _applicationParam;
  private final NetReceiveListener _receiveListener;
}
