package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@Reflection(domainClass = SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortReflection implements MovableSimpleHttpPort {
  private final MovableInboundHttpPort underlyingPort;

  public SimpleHttpPortReflection(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  ) {
    this.underlyingPort = underlyingPortReflectionFactory.create(this);
  }

  @Mover
  @Override
  public MovableSimpleHttpPort open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableSimpleHttpPort shut() {
    underlyingPort.shut();
    return this;
  }
}
