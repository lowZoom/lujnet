package luj.net.api;

import java.util.function.Consumer;

import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.api.server.NetServer;

public interface NetContext {

  /**
   * @see #createConnection(Consumer)
   */
  @Deprecated
  NetConnection createConnection(String host, int port, Object param);

  NetConnection createConnection(Consumer<NetConnection.Config> config);

  ConnectionFactory createConnectionFactory();

  /**
   * @see #createServer()
   */
  @Deprecated
  void createServer(String host, int port, Object param);

  NetServer createServer();
}
