package luj.net.api.client;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.function.Consumer;

public interface ConnectionFactory {

  interface ConnectFuture extends Future<NetConnection> {

    @Override
    ConnectFuture addListener(
        GenericFutureListener<? extends Future<? super NetConnection>> listener);
  }

  interface ConnectFutureListener extends GenericFutureListener<ConnectFuture> {
    // NOOP
  }

  ConnectFuture connect(Consumer<NetConnection.Config> config);

  //TODO: ConnectionPool
}
