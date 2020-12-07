package luj.net.internal.client.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java.util.function.Consumer;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.internal.connection.NetConnFactory;

public class NetClientConnectorV2 {

  public NetClientConnectorV2(Consumer<NetConnection.Config> configFiller,
      NioEventLoopGroup workGroup, NetDisconnectListener disconnectListener) {
    _configFiller = configFiller;
    _workGroup = workGroup;
    _disconnectListener = disconnectListener;
  }

  public NetConnection connect() {
    ConfigImpl conf = new ConfigImpl();
    _configFiller.accept(conf);

    Bootstrap bootstrap = new Bootstrap()
        .group(_workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true);

    if (conf._connectTimeout > 0) {
      bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, conf._connectTimeout);
    }

    NettyClientHandler nettyHandler = new NettyClientHandler(conf._receiver, _disconnectListener);
    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline()
            .addLast(new LengthFieldBasedFrameDecoder(10 * MB, 0, 4, 0, 4))
            .addLast(nettyHandler);
      }
    });

    ChannelFuture result = bootstrap.connect(conf._host, conf._port);
    result.syncUninterruptibly();

    NetConnection conn = new NetConnFactory(result.channel(), null).create();
    nettyHandler.setLujnetConn(conn);

    return conn;
  }

  @Deprecated
  private static final int MB = 1048576;

  private final Consumer<NetConnection.Config> _configFiller;
  private final NioEventLoopGroup _workGroup;

  private final NetDisconnectListener _disconnectListener;
}
