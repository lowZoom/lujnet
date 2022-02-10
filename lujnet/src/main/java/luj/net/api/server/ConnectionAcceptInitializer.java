package luj.net.api.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelId;

public interface ConnectionAcceptInitializer {

  interface Context {

    Connection getConnection();

    Address getBindAddress();

    <T> T getBindParam();
  }

  interface Connection {

    ByteBufAllocator alloc();

    void write(ByteBuf data);

    void flush();

    void close();

    Address getRemoteAddress();
  }

  interface Address {

    String host();

    int port();
  }

  Object init(Context ctx);
}
