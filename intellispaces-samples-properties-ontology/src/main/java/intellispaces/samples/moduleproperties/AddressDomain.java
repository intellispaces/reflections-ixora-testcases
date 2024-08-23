package intellispaces.samples.moduleproperties;

import intellispaces.core.annotation.Data;
import intellispaces.core.annotation.Domain;
import intellispaces.core.annotation.Transition;

@Data
@Domain
public interface AddressDomain {

  @Transition("bb0a84a8-6aed-4235-86b7-0303da9bd6bc")
  String city();

  @Transition("30635fb7-2ec3-4bc9-b2d6-dc957de04c15")
  String street();
}
