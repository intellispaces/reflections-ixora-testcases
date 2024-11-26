package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortHandle implements MovableSimpleHttpPort {
  private final MovableInboundHttpPort operativePort;

  public SimpleHttpPortHandle(MovableInboundHttpPort operativePort) {
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
