package tech.intellispaces.ixora.samples.properties;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Dataset;
import tech.intellispaces.jaquarius.annotation.Domain;

@Dataset
@Domain("6d541c31-31cc-4d68-9680-003a5c270e55")
public interface OwnerDomain {

  @Channel("b4c2f26e-67d0-4c8b-9614-8a7e4e21390a")
  String name();

  @Channel("af10a0aa-2ec5-4aea-a2b6-a2bc0c4bdad5")
  ContactDomain contact();

  @Channel("8115e308-be48-4d02-a54e-7e37fab84ca2")
  AddressDomain address();
}
