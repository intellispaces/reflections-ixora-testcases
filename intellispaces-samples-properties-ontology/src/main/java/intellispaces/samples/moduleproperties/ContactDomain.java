package intellispaces.samples.moduleproperties;

import intellispaces.core.annotation.Data;
import intellispaces.core.annotation.Domain;
import intellispaces.core.annotation.Transition;

@Data
@Domain("09abe89e-1528-41ff-9d7d-3ef93d03e3c4")
public interface ContactDomain {

  @Transition("3e90ff80-f11a-46c4-949d-0c5904b89edb")
  int home();

  @Transition("fbba4a1d-3757-4956-b491-6dd3c0de988c")
  int office();
}
