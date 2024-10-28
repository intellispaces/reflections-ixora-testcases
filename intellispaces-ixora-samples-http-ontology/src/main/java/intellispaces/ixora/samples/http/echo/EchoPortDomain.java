package intellispaces.ixora.samples.http.echo;

import intellispaces.ixora.http.InboundHttpPortDomain;
import intellispaces.jaquarius.annotation.Channel;
import intellispaces.jaquarius.annotation.Domain;
import intellispaces.jaquarius.traverse.TraverseTypes;

@Domain("f440030a-c859-4a99-b44f-7105ea34dbec")
public interface EchoPortDomain extends InboundHttpPortDomain {

  @Channel("84506e53-c28b-4092-9f21-89a83b2f11af")
  InboundHttpPortDomain asInboundHttpPort();

  @Channel(value = "98f5ca16-4e8f-467c-8ed0-fac46e2a62cc", allowedTraverse = TraverseTypes.Moving)
  EchoPortDomain open();

  @Channel(value = "19df3550-16cc-4ea4-a34b-fb59674e973a", allowedTraverse = TraverseTypes.Moving)
  EchoPortDomain close();
}
