package luj.net.api.server;

public interface NetHeaderDeclaration {

  interface Context {

    TypeDeclaration type(Class<?> headerType);
  }

  interface TypeDeclaration {

  }

  void declare(Context ctx);
}
