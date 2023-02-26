package Dawit.learningvertx2.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;


public class MyFirstVerticle extends AbstractVerticle {

  @Override
  public void start() {
    HttpServer server1 = vertx.createHttpServer();
    HttpServer server2 = vertx.createHttpServer();
    HttpServer server3 = vertx.createHttpServer();

    //Handle Request for Server 1
    server1.requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello, This is server1000!");
    }).listen(1000, req -> {
      if(req.succeeded()) {
        System.out.println("HTTP server started on port 1000");
      } else {
        System.out.println("HTTP server failed on port 1000");
      }
    });

    //Handle Request for Server 2
    server2.requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello, This is server2000!");
    }).listen(2000, req -> {
      if(req.succeeded()) {
        System.out.println("HTTP server started on port 2000");
      } else {
        System.out.println("HTTP server failed on port 2000");
      }
    });

    //Handle Request for Server 3
    server3.requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello, This is server3000!");
    }).listen(3000, req -> {
      if(req.succeeded()) {
        System.out.println("HTTP server started on port 3000");
      } else {
        System.out.println("HTTP server failed on port 3000");
      }
    });
  }

}
