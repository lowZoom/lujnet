package luj.net.internal.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import luj.net.api.NetConnection;
import luj.net.api.data.NetReceiveListener;

public class NetConnFactory {

  public NetConnFactory(String host, int port, NetReceiveListener receiveListener) {
    _host = host;
    _port = port;

    _receiveListener = receiveListener;
  }

  public NetConnection create() {
    NioEventLoopGroup workGroup = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap()
        .group(workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true);

    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 4, 0, 4));
        ch.pipeline().addLast(new NettyClientHandler(_receiveListener));
      }
    });

    try {
      ChannelFuture f = bootstrap.connect(_host, _port).sync();
      return new ConnectionImpl(f.channel());

    } catch (InterruptedException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final String _host;
  private final int _port;

  private final NetReceiveListener _receiveListener;
}
