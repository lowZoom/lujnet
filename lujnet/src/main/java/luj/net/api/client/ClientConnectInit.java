package luj.net.api.client;

import io.netty.channel.socket.SocketChannel;

public interface ClientConnectInit {

  interface Context {

    SocketChannel getChannel();

//    <T> T getConnectParam();
  }

  void onInit(Context ctx);
}
