package luj.net.api;

import luj.net.internal.context.NetContextFactory;
import org.springframework.context.ApplicationContext;

public enum LujNet {
  ;

  public static NetContext create(ApplicationContext appContext) {
    return new NetContextFactory(appContext).create();
  }
}
