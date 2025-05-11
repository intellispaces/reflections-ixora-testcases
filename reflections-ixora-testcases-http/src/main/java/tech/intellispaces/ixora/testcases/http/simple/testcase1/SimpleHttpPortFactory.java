package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.object.reference.DownwardObjectFactory;

@Factory
public class SimpleHttpPortFactory implements SimpleHttpPortAssistantCustomizer {

  @Override
  public MovableSimpleHttpPortHandle create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    return new SimpleHttpPortImplWrapper(underlyingPortHandleFactory);
  }
}
