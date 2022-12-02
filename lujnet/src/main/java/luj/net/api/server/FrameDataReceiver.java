package luj.net.api.server;

import io.netty.buffer.ByteBuf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
public interface FrameDataReceiver {

  @Deprecated
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @interface Head {
    // NOOP
  }

  interface Context {

    ByteBuf getLastFrame();

    <T> T getConnectionState();

    Result then();
  }

  interface Result {

    Result waitBytes(int byteCount);

    Result nextReceiver(FrameDataReceiver receiver);
  }

  Result receive(Context ctx) throws Exception;
}
