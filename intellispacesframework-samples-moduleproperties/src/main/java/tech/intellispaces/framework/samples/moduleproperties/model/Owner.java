package tech.intellispaces.framework.samples.moduleproperties.model;

import tech.intellispaces.framework.core.annotation.Data;
import tech.intellispaces.framework.core.annotation.Domain;
import tech.intellispaces.framework.core.annotation.Transition;

@Data
@Domain
public interface Owner {

  @Transition("b4c2f26e-67d0-4c8b-9614-8a7e4e21390a")
  String name();

  @Transition("af10a0aa-2ec5-4aea-a2b6-a2bc0c4bdad5")
  Contact contact();

  @Transition("8115e308-be48-4d02-a54e-7e37fab84ca2")
  Address address();
}
