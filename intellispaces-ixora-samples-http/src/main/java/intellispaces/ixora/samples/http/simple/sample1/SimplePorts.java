package intellispaces.ixora.samples.http.simple.sample1;

import intellispaces.ixora.http.MovableInboundHttpPort;

public interface SimplePorts {

  static MovableSimpleHttpPort get(MovableInboundHttpPort operativePort) {
    return new SimpleHttpPortHandleImpl(operativePort);
  }

  static MovableSimpleHttpPort getAndLink(MovableInboundHttpPort operativePort) {
    MovableSimpleHttpPort logicalPort = SimplePorts.get(operativePort);
    operativePort.addProjection(SimpleHttpPortDomain.class, logicalPort);
    return logicalPort;
  }
}
