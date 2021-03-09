package luj.net.api.connection;

public interface NetDisconnectListener {

  interface Context {

    <T> T getConnectionState();

    @Deprecated
    <T> T getApplicationParam();
  }

  void onDisconnect(Context ctx);
}
