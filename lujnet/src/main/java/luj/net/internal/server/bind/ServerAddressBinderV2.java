package luj.net.internal.server.bind;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import java.util.List;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;
import luj.net.api.server.ServerAcceptInit;
import luj.net.internal.server.accept.AcceptInitInvokerV2;

public class ServerAddressBinderV2 {

  public ServerAddressBinderV2(EventLoopGroup loopGroup, String host, int port, ServerAcceptInit acceptInitializer, List<FrameDataReceiver> frameReceivers, NetDisconnectListener disconnectListener, Object bindParam) {
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
        new AcceptInitInvokerV2(_acceptInitializer, ch, _bindParam).invoke();
      }
    });

    bootstrap.bind(_host, _port);
  }

  private final EventLoopGroup _loopGroup;
  private final String _host;
  private final int _port;

  private final ServerAcceptInit _acceptInitializer;
  private final List<FrameDataReceiver> _frameReceivers;
  private final NetDisconnectListener _disconnectListener;

  private final Object _bindParam;
}
