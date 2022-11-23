package luj.net.api;

import java.util.function.Consumer;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.api.server.NetServer;

public interface NetContext {

  @Deprecated
  NetConnection createConnection(Consumer<NetConnection.Config> config);

  ConnectionFactory createConnectionFactory();

  NetServer createServer();
}
