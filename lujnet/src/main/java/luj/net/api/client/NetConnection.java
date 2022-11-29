package luj.net.api.client;

import io.netty.buffer.ByteBuf;
import java.util.function.Consumer;
import luj.net.api.connection.NetDisconnectListener;
import luj.net.api.server.FrameDataReceiver;

public interface NetConnection {

  interface Config {

    Config host(String val);

    Config port(int val);

    Config connectTimeoutMillis(int val);

    Config listener(Consumer<Listener> val);

    @Deprecated
    Config frameReceiver(FrameDataReceiver receiver);

    @Deprecated
    Config disconnectListener(NetDisconnectListener listener);
  }

  interface Listener {

    Listener init(ClientConnectInit val);

//    disconnect
  }

  void send(byte[] data);

  void send(ByteBuf data);

  void close();

  boolean isClosed();
}
