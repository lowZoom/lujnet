package luj.net.api.client;

public interface NetConnection {

  void send(byte[] data);

  void close();

  <T> T getApplicationParam();
}
