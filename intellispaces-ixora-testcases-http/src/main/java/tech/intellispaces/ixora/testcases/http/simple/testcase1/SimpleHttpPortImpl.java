package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;

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
