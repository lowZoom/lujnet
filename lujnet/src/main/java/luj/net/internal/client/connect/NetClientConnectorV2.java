package luj.net.internal.client.connect;

import com.google.common.collect.ImmutableList;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.function.Consumer;
import luj.net.api.client.NetConnection;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.connection.NetConnFactory;
import luj.net.internal.receive.init.FrameReceiveStateFactory;

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

    NettyClientHandler nettyHandler = new NettyClientHandler();
    nettyHandler._disconnectListener = _disconnectListener;

    FrameDataReceiver frameReceiver = conf._frameReceiver;
    nettyHandler._frameReceiver = frameReceiver;

    nettyHandler._receiveState = new FrameReceiveStateFactory(
        frameReceiver == null ? ImmutableList.of() : ImmutableList.of(frameReceiver)).create();

    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(nettyHandler);
      }
    });

    ChannelFuture result = bootstrap.connect(conf._host, conf._port);
    result.syncUninterruptibly();

    NetConnection conn = new NetConnFactory(result.channel(), null).create();
    nettyHandler._lujnetConn = conn;

    return conn;
  }

  private final Consumer<NetConnection.Config> _configFiller;
  private final NioEventLoopGroup _workGroup;

  private final NetDisconnectListener _disconnectListener;
}
