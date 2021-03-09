package luj.net.internal.server;

import io.netty.channel.EventLoopGroup;
import java.util.List;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.ConnectionAcceptInitializer;
import luj.net.api.server.FrameDataReceiver;
import luj.net.api.server.NetServer;
import luj.net.internal.server.bind.ServerAddressBinder;

final class NetServerImpl implements NetServer {

  @Override
  public void bind(String host, int port, Object param) {
    new ServerAddressBinder(_loopGroup, host, port,
        _acceptInitializer, _frameReceivers, _disconnectListener, param).bind();
  }

  EventLoopGroup _loopGroup;

  ConnectionAcceptInitializer _acceptInitializer;
  List<FrameDataReceiver> _frameReceivers;

  NetDisconnectListener _disconnectListener;
}
