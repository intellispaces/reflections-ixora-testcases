package tech.intellispaces.ixora.testcases.http.simple.testcase2;

import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuideImpl;
import tech.intellispaces.ixora.internet.uri.JoinBasePathStringWithEndpointStringGuideImpl;
import tech.intellispaces.ixora.internet.uri.SplitUriPathStringToPartsGuideImpl;
import tech.intellispaces.ixora.testcases.http.simple.AbstractSimpleHttpModule;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    JoinBasePathStringWithEndpointStringGuideImpl.class,
    SplitUriPathStringToPartsGuideImpl.class,
    GetUriQueryParamGuideImpl.class,
    CliConfiguration.class,
    SimpleHttpPortGuideImpl.class
})
public class SimpleHttpTestcase2 extends AbstractSimpleHttpModule {

  @Override
  protected MovableInboundHttpPort createInboundPort(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    return SimpleHttpPorts.create(underlyingPortHandleFactory).asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(SimpleHttpTestcase2.class, args).start();
  }
}
