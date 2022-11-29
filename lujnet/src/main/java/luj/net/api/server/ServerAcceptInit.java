package luj.net.api.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.socket.SocketChannel;

public interface ServerAcceptInit {

  interface Context {

    @Deprecated
    Connection getConnection();

    @Deprecated
    Address getBindAddress();

    SocketChannel getChannel();

    <T> T getBindParam();
  }

  @Deprecated //TODO: 好像不如直接用netty的连接类就好？
  interface Connection {

    ByteBufAllocator alloc();

    void write(ByteBuf data);

    void flush();

    void close();

//    ChannelId getId();

    Address getRemoteAddress();
  }

  @Deprecated
  interface Address {

    String host();

    int port();
  }

  void onInit(Context ctx) throws Exception;
}
