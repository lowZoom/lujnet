package luj.net.internal.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import luj.net.api.connection.NetReceiveListener;
import luj.net.internal.server.bind.ServerAddressBinder;

/**
 * @see ServerAddressBinder
 */
@Deprecated
public class NetServerStarter {

  public NetServerStarter(String ip, int port, Object applicationParam,
      NetReceiveListener receiveListener) {
    _ip = ip;
    _port = port;
    _applicationParam = applicationParam;
    _receiveListener = receiveListener;
  }

  public void start() {
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(new NioEventLoopGroup());
    bootstrap.channel(NioServerSocketChannel.class);

    bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
            .addLast(new LengthFieldBasedFrameDecoder(16 * 1024 * 1024, 0, 4, 0, 4))
            .addLast(new NettyServerHandler(_applicationParam, _receiveListener));
//            .addLast(NettyServerHandlerV2.create(_applicationParam, _receiveListener));
      }
    });

    bootstrap.bind(_ip, _port);
  }

  private final String _ip;
  private final int _port;

  //  private final List<FrameDataReceiver> _receiverList;
  private final NetReceiveListener _receiveListener;

  private final Object _applicationParam;
}
