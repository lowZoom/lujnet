package luj.net.api.data;

import io.netty.buffer.ByteBuf;
import luj.net.api.NetConnection;

public interface NetReceiveListener {

  interface Context {

    ByteBuf getData();

    NetConnection getConnection();

    <T> T getApplicationParam();
  }

  void onReceive(Context ctx) throws Exception;
}
