package tech.intellispaces.ixora.testcases.http.simple;

import java.nio.charset.StandardCharsets;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.okhttp.OkHttpPorts;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;
import tech.intellispaces.jaquarius.object.reference.ObjectReferenceFunctions;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

public abstract class AbstractSimpleHttpModule {
  private static final int PORT_NUMBER = 8080;

  @Projection
  public MovableInboundHttpPort inboundPort() {
    return createInboundPort(JettyServerPorts.factory(PORT_NUMBER));
  }

  protected abstract MovableInboundHttpPort createInboundPort(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  );

  @Projection
  public MovableOutboundHttpPortHandle outboundPort() {
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

    HttpResponseHandle res = null;
    try {
      res = outboundPort().exchange(req);
      byte[] bodyBytes = ArraysFunctions.toByteArray(toList(res.bodyStream().readAll().iterator()));
      return new String(bodyBytes, StandardCharsets.UTF_8);
    } finally {
      ObjectReferenceFunctions.unbindSilently(res);
    }
  }
}
