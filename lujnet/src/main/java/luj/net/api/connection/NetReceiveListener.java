package luj.net.api.connection;

import io.netty.buffer.ByteBuf;
import luj.net.api.client.NetConnection;

/**
 * @see luj.net.api.server.FrameDataReceiver
 */
@Deprecated
public interface NetReceiveListener {

  interface Context {

    ByteBuf getData();

    NetConnection getConnection();

    <T> T getApplicationParam();
  }

  void onReceive(Context ctx) throws Exception;
}
