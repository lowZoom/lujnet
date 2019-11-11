package luj.net.api;

public interface NetConnection {

  void send(byte[] data);

  void close();

  <T> T getApplicationParam();
}
