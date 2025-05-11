package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.ObjectHandle;
import tech.intellispaces.reflections.framework.object.reference.DownwardObjectFactory;

@ObjectHandle(SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortImpl implements MovableSimpleHttpPortHandle {
  private final MovableInboundHttpPort underlyingPort;

  public SimpleHttpPortImpl(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    this.underlyingPort = underlyingPortHandleFactory.create(this);
  }

  @Mover
  @Override
  public MovableSimpleHttpPortHandle open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableSimpleHttpPortHandle shut() {
    underlyingPort.shut();
    return this;
  }
}
