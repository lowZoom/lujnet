package luj.net.api;

public interface NetContext {

  NetConnection createConnection(String host, int port, Object attachment);
}
