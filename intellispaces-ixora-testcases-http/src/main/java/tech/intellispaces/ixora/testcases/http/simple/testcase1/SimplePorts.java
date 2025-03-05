package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.object.reference.ObjectHandles;

public interface SimplePorts {

  static MovableSimpleHttpPort get(MovableInboundHttpPort operativePort) {
    return new SimpleHttpPortImplWrapper(operativePort);
  }

  static MovableSimpleHttpPort getAndLink(MovableInboundHttpPort operativePort) {
    MovableSimpleHttpPort logicalPort = SimplePorts.get(operativePort);
    ObjectHandles.handle(operativePort).addProjection(SimpleHttpPortDomain.class, logicalPort);
    return logicalPort;
  }
}
