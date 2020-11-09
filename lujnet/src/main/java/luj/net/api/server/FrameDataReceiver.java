package luj.net.api.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface FrameDataReceiver {

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @interface Head {
    // NOOP
  }

  interface Context {

    <T> T getLastFrame();

    Result finish();
  }

  interface Result {

    Result waitBytes(int byteCount);

    Result nextReceiver(Class<? extends FrameDataReceiver> receiver);
  }

  Result receive(Context ctx);
}
