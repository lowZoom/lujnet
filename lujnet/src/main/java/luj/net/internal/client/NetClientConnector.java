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
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.connection.NetReceiveListener;
import luj.net.internal.connection.NetConnFactory;

/**
 * @see luj.net.internal.client.connect.NetClientConnectorV2
 */
@Deprecated
public class NetClientConnector {

  public NetClientConnector(String host, int port, NioEventLoopGroup workGroup,
      NetReceiveListener receiveListener, NetDisconnectListener disconnectListener,
      Object connParam) {
    _host = host;
    _port = port;
    _workGroup = workGroup;
    _receiveListener = receiveListener;
    _disconnectListener = disconnectListener;
    _connParam = connParam;
  }

  public NetConnection connect() {
    Bootstrap bootstrap = new Bootstrap()
        .group(_workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true);

    NettyClientHandler nettyHandler = new NettyClientHandler(_receiveListener, _disconnectListener);
    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline()
            .addLast(new LengthFieldBasedFrameDecoder(10 * MB, 0, 4, 0, 4))
            .addLast(nettyHandler);
      }
    });

    ChannelFuture f = bootstrap.connect(_host, _port).syncUninterruptibly();

    NetConnection conn = new NetConnFactory(f.channel(), _connParam).create();
    nettyHandler.setLujnetConn(conn);

    return conn;
  }

  private static final int MB = 1048576;

  private final String _host;
  private final int _port;
  private final NioEventLoopGroup _workGroup;

  private final NetReceiveListener _receiveListener;
  private final NetDisconnectListener _disconnectListener;

  private final Object _connParam;
}
