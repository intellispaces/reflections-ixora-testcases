package tech.intellispaces.ixora.samples.http.simple.sample2;

import tech.intellispace.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.internet.JoinUrlGuideImpl;
import tech.intellispaces.ixora.internet.SplitUriPathGuideImpl;
import tech.intellispaces.ixora.internet.UriToQueryParamGuideImpl;
import tech.intellispaces.ixora.samples.http.simple.AbstractSimpleHttpSample;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    JoinUrlGuideImpl.class,
    SplitUriPathGuideImpl.class,
    UriToQueryParamGuideImpl.class,
    CliConfiguration.class,
    SimpleHttpPortGuideImpl.class
})
public class SimpleHttpSample2 extends AbstractSimpleHttpSample {

  @Override
  protected MovableInboundHttpPort getInboundPort(int portNumber) {
    MovableInboundHttpPort operativePort = JettyServerPorts.get(
        portNumber, SimplePortExchangeChannel.class
    ).asInboundHttpPort();
    MovableSimpleHttpPort logicalPort = SimpleHttpPorts.getAndLink(operativePort);
    return logicalPort.asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.get(SimpleHttpSample2.class, args).start();
  }
}
