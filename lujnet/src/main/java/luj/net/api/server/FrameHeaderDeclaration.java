package luj.net.api.server;

import java.util.function.Function;

public interface FrameHeaderDeclaration {

  interface Context {

    Header header();
  }

  interface Header {

    Header size(int byteCount);

    <T> HeaderType<T> type(Class<T> type);
  }

  interface HeaderType<T> {

    HeaderType<T> field(Function<T, ?> field, int size);
  }

  void declare(Context ctx);
}
