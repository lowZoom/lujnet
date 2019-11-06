package luj.net.api.data;

import io.netty.buffer.ByteBuf;

public interface NetReceiveListener {

  interface Context {

    ByteBuf getData();

    <T> T getApplicationParam();
  }

  void onReceive(Context ctx) throws Exception;
}
