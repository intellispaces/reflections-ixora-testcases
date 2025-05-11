package tech.intellispaces.ixora.testcases.http.simple.testcase2;

import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.ixora.http.InboundHttpPortDomain;
import tech.intellispaces.ixora.http.annotation.HttpPort;
import tech.intellispaces.reflections.framework.annotation.Channel;
import tech.intellispaces.reflections.framework.annotation.Domain;
import tech.intellispaces.reflections.framework.traverse.TraverseTypes;

@HttpPort(SimpleHttpPortOntology.class)
@Domain("51e05981-c19c-4ad6-94e0-cf70e76ed47a")
public interface SimpleHttpPortDomain extends InboundHttpPortDomain {

  @Channel("12d3d3b3-e668-4be7-8960-8bc11d5eda0a")
  InboundHttpPortDomain asInboundHttpPort();

  @Override
  @Channel(value = "e3f9f3b4-e2ae-49fc-bc0a-f65f70f82189", allowedTraverse = TraverseTypes.Moving)
  SimpleHttpPortDomain open();

  @Override
  @Channel(value = "2092e749-cd80-4788-aa23-4f6784e26000", allowedTraverse = TraverseTypes.Moving)
  SimpleHttpPortDomain shut();

  @Override
  @Channel(
      value = "346e083f-5541-465b-ac44-e9688ffe90bf",
      name = "SimplePortExchangeChannel",
      allowedTraverse = TraverseTypes.MappingOfMoving)
  HttpResponseDomain exchange(HttpRequestDomain request);
}
