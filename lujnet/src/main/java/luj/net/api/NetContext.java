package luj.net.api;

import luj.net.api.client.NetConnection;

public interface NetContext {

  NetConnection createConnection(String host, int port, Object param);

  void createServer(String host, int port, Object param);
}
