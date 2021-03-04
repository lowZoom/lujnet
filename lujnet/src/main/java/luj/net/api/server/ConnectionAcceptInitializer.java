package luj.net.api.server;

public interface ConnectionAcceptInitializer {

  interface Context {

    Connection getConnection();

    <T> T getBindParam();
  }

  interface Connection {


  }

  Object init(Context ctx);
}
