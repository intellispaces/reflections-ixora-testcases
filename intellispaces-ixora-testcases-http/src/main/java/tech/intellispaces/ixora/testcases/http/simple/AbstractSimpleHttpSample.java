package tech.intellispaces.ixora.testcases.http.simple;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpMethodsCustomizer;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpRequestsCustomizer;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.ixora.okhttp.OkHttpPorts;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.object.reference.ObjectHandles;
import tech.intellispaces.jaquarius.object.reference.ObjectReferenceFunctions;

import java.nio.charset.StandardCharsets;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

public abstract class AbstractSimpleHttpSample {
  private static final int PORT_NUMBER = 8080;

  protected abstract MovableInboundHttpPort getInboundPort(int portNumber);

  @Projection
  public MovableInboundHttpPort inboundPort() {
    return getInboundPort(PORT_NUMBER);
  }

  @Projection
  public MovableOutboundHttpPortHandle outboundPort() {
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
    HttpRequest request = HttpRequestsCustomizer.get(HttpMethodsCustomizer.get(), "http://localhost:" + PORT_NUMBER + endpoint);

    HttpResponseHandle response = null;
    try {
      response = outboundPort().exchange(request);

      byte[] responseBodyBytes = ArraysFunctions.toByteArray(toList(response.bodyStream().readAll().iterator()));
      return new String(responseBodyBytes, StandardCharsets.UTF_8);
    } finally {
      ObjectReferenceFunctions.releaseSilently(ObjectHandles.handle(response));
    }
  }
}
