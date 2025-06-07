package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@Factory
public class SimpleHttpPortFactory implements SimpleHttpPortAssistantCustomizer {

  @Override
  public MovableSimpleHttpPort create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  ) {
    return new SimpleHttpPortReflectionWrapper(underlyingPortReflectionFactory);
  }
}
