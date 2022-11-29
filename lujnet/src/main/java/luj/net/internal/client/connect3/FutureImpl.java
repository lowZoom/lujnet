package luj.net.internal.client.connect3;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.AbstractFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.concurrent.TimeUnit;
import luj.net.api.client.ConnectionFactory;
import luj.net.api.client.NetConnection;
import luj.net.internal.connection.NetConnFactory;

@Deprecated
final class FutureImpl extends AbstractFuture<NetConnection> implements ConnectionFactory.ConnectFuture {

  @Override
  public NetConnection getNow() {
    return _result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ConnectionFactory.ConnectFuture addListener(GenericFutureListener<? extends Future<? super NetConnection>> listener) {
    _nettyFuture.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) throws Exception {
        _result = new NetConnFactory(future.channel()).create();
        ((GenericFutureListener<FutureImpl>) listener).operationComplete(FutureImpl.this);
      }
    });
    return this;
  }

  @SafeVarargs
  @Override
  public final Future<NetConnection> addListeners(GenericFutureListener<? extends Future<? super NetConnection>>... listeners) {
    throw new UnsupportedOperationException("addListeners");
  }

  @Override
  public Future<NetConnection> removeListener(GenericFutureListener<? extends Future<? super NetConnection>> listener) {
    throw new UnsupportedOperationException("removeListener");
  }

  @SafeVarargs
  @Override
  public final Future<NetConnection> removeListeners(GenericFutureListener<? extends Future<? super NetConnection>>... listeners) {
    throw new UnsupportedOperationException("removeListeners");
  }

  @Override
  public boolean isSuccess() {
    return _nettyFuture.isSuccess();
  }

  @Override
  public boolean isCancellable() {
    return _nettyFuture.isCancellable();
  }

  @Override
  public Throwable cause() {
    return _nettyFuture.cause();
  }

  @Override
  public Future<NetConnection> sync() throws InterruptedException {
    _nettyFuture.sync();
    return this;
  }

  @Override
  public Future<NetConnection> syncUninterruptibly() {
    _nettyFuture.syncUninterruptibly();
    return this;
  }

  @Override
  public Future<NetConnection> await() throws InterruptedException {
    _nettyFuture.await();
    return this;
  }

  @Override
  public Future<NetConnection> awaitUninterruptibly() {
    _nettyFuture.awaitUninterruptibly();
    return this;
  }

  @Override
  public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
    return _nettyFuture.await(timeout, unit);
  }

  @Override
  public boolean await(long timeoutMillis) throws InterruptedException {
    return _nettyFuture.await(timeoutMillis);
  }

  @Override
  public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
    return _nettyFuture.awaitUninterruptibly(timeout, unit);
  }

  @Override
  public boolean awaitUninterruptibly(long timeoutMillis) {
    return _nettyFuture.awaitUninterruptibly(timeoutMillis);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return _nettyFuture.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return _nettyFuture.isCancelled();
  }

  @Override
  public boolean isDone() {
    return _nettyFuture.isDone();
  }

  NetConnection _result;

  ChannelFuture _nettyFuture;
}
