package luj.net.api.server;

public interface ConnectionAcceptInitializer {

  interface Context {

    Address getBindAddress();

    <T> T getBindParam();
  }

  interface Address {

    String host();

    int port();
  }

  Object init(Context ctx);
}
