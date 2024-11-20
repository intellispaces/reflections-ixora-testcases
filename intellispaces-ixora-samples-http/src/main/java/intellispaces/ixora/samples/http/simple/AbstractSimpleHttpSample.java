package intellispaces.ixora.samples.http.simple;

import intellispace.ixora.okhttp.OkHttpPorts;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.ixora.http.HttpMethods;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpRequests;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.http.MovableOutboundHttpPort;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Projection;
import intellispaces.jaquarius.annotation.Startup;
import intellispaces.jaquarius.object.ObjectFunctions;
import tech.intellispaces.entity.collection.ArraysFunctions;

import java.nio.charset.StandardCharsets;

public abstract class AbstractSimpleHttpSample {
  private static final int PORT_NUMBER = 8080;

  protected abstract MovableInboundHttpPort getInboundPort(int portNumber);

  @Projection
  public MovableInboundHttpPort inboundPort() {
    return getInboundPort(PORT_NUMBER);
  }

  @Projection
  public MovableOutboundHttpPort outboundPort() {
    return OkHttpPorts.get().asOutboundHttpPort();
  }

  @Startup
  public void startup(@Inject MovableConsole console) {
    // Open inbound port
    inboundPort().open();

    // Call inbound port
    console.println("Current date: " + call("/date/current"));
    console.println("Welcome message: " + call("/welcome/hello?name=John"));

    // Close inbound port
    inboundPort().close();
  }

  private String call(String endpoint) {
    HttpRequest request = HttpRequests.get(HttpMethods.get(), "http://localhost:" + PORT_NUMBER + endpoint);

    HttpResponse response = null;
    try {
      response = outboundPort().exchange(request);

      byte[] responseBodyBytes = ArraysFunctions.toByteArray(response.bodyStream().readAll().nativeList());
      return new String(responseBodyBytes, StandardCharsets.UTF_8);
    } finally {
      ObjectFunctions.releaseSilently(response);
    }
  }
}
