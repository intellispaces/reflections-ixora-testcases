package tech.intellispaces.ixora.samples.http.simple;

import tech.intellispace.ixora.okhttp.OkHttpPorts;
import tech.intellispaces.general.collection.ArraysFunctions;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.jaquarius.object.reference.ObjectHandleFunctions;

import java.nio.charset.StandardCharsets;

public abstract class AbstractSimpleHttpSample {
  private static final int PORT_NUMBER = 8080;

  protected abstract MovableInboundHttpPortHandle getInboundPort(int portNumber);

  @Projection
  public MovableInboundHttpPortHandle inboundPort() {
    return getInboundPort(PORT_NUMBER);
  }

  @Projection
  public MovableOutboundHttpPortHandle outboundPort() {
    return OkHttpPorts.get().asOutboundHttpPort();
  }

  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    // Open inbound port
    inboundPort().open();

    // Call inbound port
    console.println("Current date: " + call("/date/current"));
    console.println("Welcome message: " + call("/welcome/hello?name=John"));

    // Close inbound port
    inboundPort().close();
  }

  private String call(String endpoint) {
    HttpRequestHandle request = HttpRequests.get(HttpMethods.get(), "http://localhost:" + PORT_NUMBER + endpoint);

    HttpResponseHandle response = null;
    try {
      response = outboundPort().exchange(request);

      byte[] responseBodyBytes = ArraysFunctions.toByteArray(response.bodyStream().readAll().nativeList());
      return new String(responseBodyBytes, StandardCharsets.UTF_8);
    } finally {
      ObjectHandleFunctions.releaseSilently(response);
    }
  }
}
