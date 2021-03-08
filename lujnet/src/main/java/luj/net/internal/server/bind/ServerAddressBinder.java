package luj.net.internal.server.bind;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.List;
import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.receive.init.FrameReceiveStateFactory;
import luj.net.internal.server.accept.AcceptInitInvoker;

public class ServerAddressBinder {

  public ServerAddressBinder(EventLoopGroup loopGroup, String host, int port,
      ConnectionAcceptInitializer acceptInitializer, List<FrameDataReceiver> frameReceivers,
      Object bindParam) {
    _loopGroup = loopGroup;
    _host = host;
    _port = port;
    _acceptInitializer = acceptInitializer;
    _frameReceivers = frameReceivers;
    _bindParam = bindParam;
  }

  public void bind() {
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.channel(NioServerSocketChannel.class);
    bootstrap.group(_loopGroup);

    bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(createNettyHandler(ch));
      }
    });

    bootstrap.bind(_host, _port);
  }

  private NettyServerHandlerV2 createNettyHandler(SocketChannel channel) {
    NettyServerHandlerV2 handler = new NettyServerHandlerV2();
    handler._connState = new AcceptInitInvoker(
        _acceptInitializer, channel, _host, _port, _bindParam).invoke();

    handler._receiveState = new FrameReceiveStateFactory(_frameReceivers).create();
    return handler;
  }

  private final EventLoopGroup _loopGroup;
  private final String _host;
  private final int _port;

  private final ConnectionAcceptInitializer _acceptInitializer;
  private final List<FrameDataReceiver> _frameReceivers;

  private final Object _bindParam;
}
