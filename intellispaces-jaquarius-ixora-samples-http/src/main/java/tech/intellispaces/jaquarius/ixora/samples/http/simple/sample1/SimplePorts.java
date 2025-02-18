package tech.intellispaces.jaquarius.ixora.samples.http.simple.sample1;

import tech.intellispaces.jaquarius.ixora.http.MovableInboundHttpPortHandle;

public interface SimplePorts {

  static MovableSimpleHttpPortHandle get(MovableInboundHttpPortHandle operativePort) {
    return new SimpleHttpPortHandleImplWrapper(operativePort);
  }

  static MovableSimpleHttpPortHandle getAndLink(MovableInboundHttpPortHandle operativePort) {
    MovableSimpleHttpPortHandle logicalPort = SimplePorts.get(operativePort);
    operativePort.addProjection(SimpleHttpPortDomain.class, logicalPort);
    return logicalPort;
  }
}
