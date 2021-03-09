package luj.net.api.server;

import io.netty.buffer.ByteBuf;

public interface ConnectionAcceptInitializer {

  interface Context {

    Connection getConnection();

    Address getBindAddress();

    <T> T getBindParam();
  }

  interface Connection {

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
