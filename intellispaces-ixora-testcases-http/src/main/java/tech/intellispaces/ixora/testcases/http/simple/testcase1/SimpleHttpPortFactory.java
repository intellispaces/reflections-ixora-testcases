package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;

@ObjectFactory
public class SimpleHttpPortFactory implements SimpleHttpPortAssistantExtension {

  @Override
  public MovableSimpleHttpPortHandle create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    return new SimpleHttpPortImplWrapper(underlyingPortHandleFactory);
  }
}
