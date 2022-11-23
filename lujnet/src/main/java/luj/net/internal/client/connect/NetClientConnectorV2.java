package luj.net.internal.client.connect;

import com.google.common.collect.ImmutableList;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import luj.net.api.client.NetConnection;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.connection.NetConnFactory;
import luj.net.internal.receive.init.FrameReceiveStateFactory;

import java.util.function.Consumer;

public class NetClientConnectorV2 {

  public NetClientConnectorV2(Consumer<NetConnection.Config> configFiller, NioEventLoopGroup workGroup) {
    _configFiller = configFiller;
    _workGroup = workGroup;
  }

  public NetConnection connect() {
    ChannelFuture result = connect2();
    result.awaitUninterruptibly();

    Channel channel = result.isSuccess() ? result.channel() : null;
    return new NetConnFactory(channel, null).create();
  }

  public ChannelFuture connect2() {
    ConfigImpl conf = new ConfigImpl();
    _configFiller.accept(conf);

    Bootstrap bootstrap = new ClientBootMaker(_workGroup).make();
    if (conf._connectTimeout > 0) {
      bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, conf._connectTimeout);
    }

    NettyClientHandler nettyHandler = new NettyClientHandler();
    nettyHandler._active = false;
    nettyHandler._disconnectListener = conf._disconnectListener;

    FrameDataReceiver frameReceiver = conf._frameReceiver;
    nettyHandler._frameReceiver = frameReceiver;

    nettyHandler._receiveState = new FrameReceiveStateFactory(
        (frameReceiver == null) ? ImmutableList.of() : ImmutableList.of(frameReceiver)).create();

    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(nettyHandler);
      }
    });

    return bootstrap.connect(conf._host, conf._port);
  }

  private final Consumer<NetConnection.Config> _configFiller;

  private final NioEventLoopGroup _workGroup;
}
