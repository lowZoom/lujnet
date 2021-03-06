package luj.net.internal.server;

import io.netty.channel.nio.NioEventLoopGroup;
import java.util.List;
import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.FrameDataReceiver;
import luj.net.api.server.NetServer;

public class NetServerFactory {

  public NetServerFactory(ConnectionAcceptInitializer acceptInitializer,
      List<FrameDataReceiver> frameReceivers) {
    _acceptInitializer = acceptInitializer;
    _frameReceivers = frameReceivers;
  }

  public NetServer create() {
    NioEventLoopGroup loopGroup = new NioEventLoopGroup();

    NetServerImpl serverImpl = new NetServerImpl();
    serverImpl._loopGroup = loopGroup;

    serverImpl._acceptInitializer = _acceptInitializer;
    serverImpl._frameReceivers = _frameReceivers;

    return serverImpl;
  }

  private final ConnectionAcceptInitializer _acceptInitializer;

  private final List<FrameDataReceiver> _frameReceivers;
}
