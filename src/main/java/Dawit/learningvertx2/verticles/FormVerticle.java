package Dawit.learningvertx2.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class FormVerticle extends AbstractVerticle {

  @Override
  public void start() {

    Vertx vertx = Vertx.vertx();

    vertx.createHttpServer().requestHandler(req -> {
      String name = req.getParam("name");
      req.response().putHeader("Content-Type", "text/plain").end("Hello " + name);
    }).listen(5050);

  }
}
