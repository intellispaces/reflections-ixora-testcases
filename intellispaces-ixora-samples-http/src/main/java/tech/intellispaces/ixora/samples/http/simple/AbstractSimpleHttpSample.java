package tech.intellispaces.ixora.samples.http.simple;

import tech.intellispace.ixora.okhttp.OkHttpPorts;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.MovableOutboundHttpPort;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.object.ObjectHandleFunctions;
import tech.intellispaces.general.collection.ArraysFunctions;

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
      ObjectHandleFunctions.releaseSilently(response);
    }
  }
}
