package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(SimpleHttpPortDomain.class)
public abstract class SimpleHttpPortHandleDefault implements MovableSimpleHttpPortHandle {
  private final MovableInboundHttpPortHandle operativePort;

  public SimpleHttpPortHandleDefault(MovableInboundHttpPortHandle operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPortHandle getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableSimpleHttpPortHandle open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableSimpleHttpPortHandle close() {
    operativePort.close();
    return this;
  }
}
