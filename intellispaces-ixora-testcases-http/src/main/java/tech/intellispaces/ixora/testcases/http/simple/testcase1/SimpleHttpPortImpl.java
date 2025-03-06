package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortImpl implements MovableSimpleHttpPort {
  private final MovableInboundHttpPort operativePort;

  public SimpleHttpPortImpl(MovableInboundHttpPort operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPort getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableSimpleHttpPort open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableSimpleHttpPort close() {
    operativePort.close();
    return this;
  }
}
