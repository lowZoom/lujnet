package luj.net.internal.context;

import luj.ava.spring.Internal;
import luj.net.api.NetContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public class NetContextFactory {

  public NetContextFactory(ApplicationContext appContext, Object appParam) {
    _appContext = appContext;
    _appParam = appParam;
  }

  public NetContext create() {
    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.setParent(_appContext);

      ctx.register(InjectConf.class);
      ctx.refresh();

      InjectRoot rootBean = ctx.getBean(InjectRoot.class);
      return new NetContextImpl(rootBean, _appParam);
    }
  }

  @ComponentScan(basePackages = "luj.net.internal.context",
      includeFilters = @ComponentScan.Filter(Internal.class))
  static class InjectConf {
    // NOOP
  }

  private final ApplicationContext _appContext;

  private final Object _appParam;
}
