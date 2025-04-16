package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.ixora.http.InboundHttpPortDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.traverse.TraverseTypes;

@Domain("f440030a-c859-4a99-b44f-7105ea34dbec")
public interface SimpleHttpPortDomain extends InboundHttpPortDomain {

  @Channel("84506e53-c28b-4092-9f21-89a83b2f11af")
  InboundHttpPortDomain asInboundHttpPort();

  @Channel(value = "98f5ca16-4e8f-467c-8ed0-fac46e2a62cc", allowedTraverse = TraverseTypes.Moving)
  SimpleHttpPortDomain open();

  @Channel(value = "19df3550-16cc-4ea4-a34b-fb59674e973a", allowedTraverse = TraverseTypes.Moving)
  SimpleHttpPortDomain shut();

  @Override
  @Channel(
      value = "fd0fdb02-f405-4a27-813c-40980f48c55b",
      name = "SimpleHttpPortExchangeChannel",
      allowedTraverse = {TraverseTypes.MappingOfMoving}
  )
  HttpResponseDomain exchange(HttpRequestDomain request);
}
