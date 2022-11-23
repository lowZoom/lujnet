package luj.net.internal.server.bind;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.FrameDataReceiver;
import luj.net.internal.receive.init.FrameReceiveStateFactory;
import luj.net.internal.server.accept.AcceptInitInvoker;

import java.util.List;

public class ServerAddressBinder {

  public ServerAddressBinder(EventLoopGroup loopGroup, String host, int port, ConnectionAcceptInitializer acceptInitializer, List<FrameDataReceiver> frameReceivers, NetDisconnectListener disconnectListener, Object bindParam) {
    _loopGroup = loopGroup;
    _host = host;
    _port = port;
    _acceptInitializer = acceptInitializer;
    _frameReceivers = frameReceivers;
    _disconnectListener = disconnectListener;
    _bindParam = bindParam;
  }

  public void bind() {
    ServerBootstrap bootstrap = new ServerBootMaker(_loopGroup, _loopGroup).make();

    bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(createNettyHandler(ch));
      }
    });

    bootstrap.bind(_host, _port);
  }

  private NettyServerHandlerV2 createNettyHandler(SocketChannel channel) throws Exception {
    NettyServerHandlerV2 handler = new NettyServerHandlerV2();
    handler._receiveState = new FrameReceiveStateFactory(_frameReceivers).create();
    handler._disconnectListener = _disconnectListener;

    handler._connState = new AcceptInitInvoker(
        _acceptInitializer, channel, _host, _port, _bindParam).invoke();

    return handler;
  }

  private final EventLoopGroup _loopGroup;
  private final String _host;
  private final int _port;

  private final ConnectionAcceptInitializer _acceptInitializer;
  private final List<FrameDataReceiver> _frameReceivers;
  private final NetDisconnectListener _disconnectListener;

  private final Object _bindParam;
}
