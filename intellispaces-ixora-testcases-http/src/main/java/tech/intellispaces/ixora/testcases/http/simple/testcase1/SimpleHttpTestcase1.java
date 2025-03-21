package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuideImpl;
import tech.intellispaces.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.testcases.http.simple.AbstractSimpleHttpSample;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    SimpleHttpPortExchangeGuideImpl.class,
    GetUriQueryParamGuideImpl.class,
    CliConfiguration.class
})
public class SimpleHttpTestcase1 extends AbstractSimpleHttpSample {

  @Override
  protected MovableInboundHttpPort getInboundPort(int portNumber) {
    MovableInboundHttpPort operativePort = JettyServerPorts.get(
        portNumber, SimpleHttpPortExchangeChannel.class
    ).asInboundHttpPort();
    MovableSimpleHttpPort logicalPort = SimplePortsCustomizer.getAndLink(operativePort);
    return logicalPort.asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(SimpleHttpTestcase1.class, args).start();
  }
}
