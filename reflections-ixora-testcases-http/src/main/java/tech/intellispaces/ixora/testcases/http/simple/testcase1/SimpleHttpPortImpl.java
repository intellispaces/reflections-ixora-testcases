package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@Reflection(SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortImpl implements MovableSimpleHttpPortReflection {
  private final MovableInboundHttpPort underlyingPort;

  public SimpleHttpPortImpl(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  ) {
    this.underlyingPort = underlyingPortReflectionFactory.create(this);
  }

  @Mover
  @Override
  public MovableSimpleHttpPortReflection open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableSimpleHttpPortReflection shut() {
    underlyingPort.shut();
    return this;
  }
}
