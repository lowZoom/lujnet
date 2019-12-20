package luj.net.api.data;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;

public interface NetReceiveListener {

  interface Context {

    ByteBuf getData();

    <T> T getApplicationParam();

    NetConnection getConnection();
  }

  void onReceive(Context ctx) throws Exception;
}
