package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;

public interface SimplePorts {

  static MovableSimpleHttpPortHandle get(MovableInboundHttpPortHandle operativePort) {
    return new SimpleHttpPortHandleDefaultImpl(operativePort);
  }

  static MovableSimpleHttpPortHandle getAndLink(MovableInboundHttpPortHandle operativePort) {
    MovableSimpleHttpPortHandle logicalPort = SimplePorts.get(operativePort);
    operativePort.addProjection(SimpleHttpPortDomain.class, logicalPort);
    return logicalPort;
  }
}
