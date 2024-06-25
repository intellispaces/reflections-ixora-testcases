package tech.intellispaces.framework.samples.moduleproperties.model;

import tech.intellispaces.framework.core.annotation.Data;
import tech.intellispaces.framework.core.annotation.Domain;
import tech.intellispaces.framework.core.annotation.Transition;

@Data
@Domain
public interface Contact {

  @Transition("3e90ff80-f11a-46c4-949d-0c5904b89edb")
  int home();

  @Transition("fbba4a1d-3757-4956-b491-6dd3c0de988c")
  int office();
}
