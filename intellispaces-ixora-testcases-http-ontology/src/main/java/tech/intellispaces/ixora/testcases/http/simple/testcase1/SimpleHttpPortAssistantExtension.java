package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.annotation.AssistantExtension;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;

@AssistantExtension(SimpleHttpPortDomain.class)
public interface SimpleHttpPortAssistantExtension {

  MovableSimpleHttpPortHandle create(DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory);
}
