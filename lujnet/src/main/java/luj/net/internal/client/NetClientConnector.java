package luj.net.internal.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import luj.net.api.client.NetConnection;
import luj.net.api.data.NetReceiveListener;
import luj.net.internal.connection.NetConnFactory;

public class NetClientConnector {

  public NetClientConnector(String host, int port, NioEventLoopGroup workGroup,
      NetReceiveListener receiveListener, Object connParam) {
    _host = host;
    _port = port;
    _workGroup = workGroup;
    _receiveListener = receiveListener;
    _connParam = connParam;
  }

  public NetConnection connect() {
    Bootstrap bootstrap = new Bootstrap()
        .group(_workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true);

    NettyClientHandler nettyHandler = new NettyClientHandler(_receiveListener);
    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline()
            .addLast(new LengthFieldBasedFrameDecoder(MB, 0, 4, 0, 4))
            .addLast(nettyHandler);
      }
    });

    try {
      ChannelFuture f = bootstrap.connect(_host, _port).sync();

      NetConnection conn = new NetConnFactory(f.channel(), _connParam).create();
      nettyHandler.setLujnetConn(conn);

      return conn;

    } catch (InterruptedException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private static final int MB = 1048576;

  private final String _host;
  private final int _port;
  private final NioEventLoopGroup _workGroup;

  private final NetReceiveListener _receiveListener;
  private final Object _connParam;
}