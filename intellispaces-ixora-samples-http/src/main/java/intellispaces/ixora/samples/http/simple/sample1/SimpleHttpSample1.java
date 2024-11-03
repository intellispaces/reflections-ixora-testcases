package intellispaces.ixora.samples.http.simple.sample1;

import intellispace.ixora.jetty.JettyServerPorts;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.internet.UriToQueryParamGuideImpl;
import intellispaces.ixora.samples.http.simple.AbstractSimpleHttpSample;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.system.Modules;

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
    Modules.get(SimpleHttpSample1.class, args).start();
  }
}
