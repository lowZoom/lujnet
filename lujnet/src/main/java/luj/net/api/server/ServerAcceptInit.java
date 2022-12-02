package luj.net.api.server;

import io.netty.channel.socket.SocketChannel;

public interface ServerAcceptInit {

  interface Context {

    SocketChannel getChannel();

    <T> T getBindParam();
  }

  void onInit(Context ctx) throws Exception;
}
