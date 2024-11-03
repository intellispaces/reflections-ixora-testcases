package intellispaces.ixora.samples.http.simple.sample2;

import intellispace.ixora.jetty.JettyServerPorts;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.internet.JoinUrlGuideImpl;
import intellispaces.ixora.internet.SplitUriPathGuideImpl;
import intellispaces.ixora.internet.UriToQueryParamGuideImpl;
import intellispaces.ixora.samples.http.simple.AbstractSimpleHttpSample;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.system.Modules;

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
