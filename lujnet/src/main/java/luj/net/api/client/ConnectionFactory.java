package luj.net.api.client;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.function.Consumer;

public interface ConnectionFactory {

  @Deprecated
  interface ConnectFuture extends Future<NetConnection> {

    /**
     * @see ConnectFutureListener
     */
    @Override
    ConnectFuture addListener(
        GenericFutureListener<? extends Future<? super NetConnection>> listener);
  }

  @Deprecated
  interface ConnectFutureListener extends GenericFutureListener<ConnectFuture> {
    // NOOP
  }

  ChannelFuture connect(Consumer<NetConnection.Config> config);

  //TODO: ConnectionPool
}
