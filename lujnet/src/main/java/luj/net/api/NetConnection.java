package luj.net.api;

public interface NetConnection {

  void send(byte[] data);

  void close();

  NetContext getContext();

  <T> T getApplicationParam();
}
