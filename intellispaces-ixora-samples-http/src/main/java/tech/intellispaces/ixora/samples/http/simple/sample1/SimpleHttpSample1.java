package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuideImpl;
import tech.intellispaces.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.samples.http.simple.AbstractSimpleHttpSample;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    SimpleHttpPortExchangeGuideImpl.class,
    GetUriQueryParamGuideImpl.class,
    CliConfiguration.class
})
public class SimpleHttpSample1 extends AbstractSimpleHttpSample {

  @Override
  protected MovableInboundHttpPortHandle getInboundPort(int portNumber) {
    MovableInboundHttpPortHandle operativePort = JettyServerPorts.get(
        portNumber, SimpleHttpPortExchangeChannel.class
    ).asInboundHttpPort();
    MovableSimpleHttpPortHandle logicalPort = SimplePorts.getAndLink(operativePort);
    return logicalPort.asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(SimpleHttpSample1.class, args).start();
  }
}
