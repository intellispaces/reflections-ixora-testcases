package tech.intellispaces.ixora.testcases.http.simple;

import java.nio.charset.StandardCharsets;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponseReflection;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortReflection;
import tech.intellispaces.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.okhttp.OkHttpPorts;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Projection;
import tech.intellispaces.reflections.framework.annotation.Startup;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;
import tech.intellispaces.reflections.framework.reflection.ReflectionFunctions;

public abstract class AbstractSimpleHttpModule {
  private static final int PORT_NUMBER = 8080;

  @Projection
  public MovableInboundHttpPort inboundPort() {
    return createInboundPort(JettyServerPorts.factory(PORT_NUMBER));
  }

  protected abstract MovableInboundHttpPort createInboundPort(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  );

  @Projection
  public MovableOutboundHttpPortReflection outboundPort() {
    return OkHttpPorts.create().asOutboundHttpPort();
  }

  @Startup
  public void startup(@Inject MovableConsole console) {
    // Open inbound port
    inboundPort().open();

    // Call inbound port
    console.println("Current date: " + call("/date/current"));
    console.println("Welcome message: " + call("/welcome/hello?name=John"));

    // Close inbound port
    inboundPort().shut();
  }

  private String call(String endpoint) {
    HttpRequest req = HttpRequests.create(HttpMethods.get(), "http://localhost:" + PORT_NUMBER + endpoint);

    HttpResponseReflection res = null;
    try {
      res = outboundPort().exchange(req);
      byte[] bodyBytes = ArraysFunctions.toByteArray(toList(res.bodyStream().readAll().iterator()));
      return new String(bodyBytes, StandardCharsets.UTF_8);
    } finally {
      ReflectionFunctions.unbindSilently(res);
    }
  }
}
