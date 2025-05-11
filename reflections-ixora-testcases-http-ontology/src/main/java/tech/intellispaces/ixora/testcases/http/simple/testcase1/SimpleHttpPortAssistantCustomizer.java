package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.AssistantCustomizer;
import tech.intellispaces.reflections.framework.object.reference.DownwardObjectFactory;

@AssistantCustomizer(SimpleHttpPortDomain.class)
public interface SimpleHttpPortAssistantCustomizer {

  MovableSimpleHttpPortHandle create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  );
}
