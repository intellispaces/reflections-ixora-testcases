package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.annotation.AssistantCustomizer;
import tech.intellispaces.reflections.object.reference.DownwardObjectFactory;

@AssistantCustomizer(SimpleHttpPortDomain.class)
public interface SimpleHttpPortAssistantCustomizer {

  MovableSimpleHttpPortHandle create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  );
}
