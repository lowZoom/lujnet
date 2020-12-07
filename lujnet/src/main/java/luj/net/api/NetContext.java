package luj.net.api;

import java.util.function.Consumer;
import luj.net.api.client.NetConnection;

public interface NetContext {

  /**
   * @see #createConnection(Consumer)
   */
  @Deprecated
  NetConnection createConnection(String host, int port, Object param);

  NetConnection createConnection(Consumer<NetConnection.Config> config);

  void createServer(String host, int port, Object param);
}
