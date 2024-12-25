package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispace.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.internet.UriToQueryParamGuideImpl;
import tech.intellispaces.ixora.samples.http.simple.AbstractSimpleHttpSample;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    SimpleHttpPortExchangeGuideImpl.class,
    UriToQueryParamGuideImpl.class,
    CliConfiguration.class
})
public class SimpleHttpSample1 extends AbstractSimpleHttpSample {

  @Override
  protected MovableInboundHttpPort getInboundPort(int portNumber) {
    MovableInboundHttpPort operativePort = JettyServerPorts.get(
        portNumber, SimpleHttpPortExchangeChannel.class
    ).asInboundHttpPort();
    MovableSimpleHttpPort logicalPort = SimplePorts.getAndLink(operativePort);
    return logicalPort.asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(SimpleHttpSample1.class, args).start();
  }
}
