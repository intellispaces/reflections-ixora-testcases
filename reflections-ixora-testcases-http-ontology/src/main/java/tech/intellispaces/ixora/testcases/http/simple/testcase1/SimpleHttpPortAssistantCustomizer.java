package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.annotation.AssistantCustomizer;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@AssistantCustomizer(SimpleHttpPortDomain.class)
public interface SimpleHttpPortAssistantCustomizer {

  MovableSimpleHttpPort create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  );
}
