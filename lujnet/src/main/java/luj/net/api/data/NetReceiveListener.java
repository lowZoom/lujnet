package luj.net.api.data;

import io.netty.buffer.ByteBuf;

public interface NetReceiveListener {

  interface Context {

    ByteBuf data();
  }

  void onReceive(Context ctx);
}
