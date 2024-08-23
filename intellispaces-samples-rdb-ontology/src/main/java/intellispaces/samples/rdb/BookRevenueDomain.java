package intellispaces.samples.rdb;

import intellispaces.core.annotation.Data;
import intellispaces.core.annotation.Domain;
import intellispaces.core.annotation.Transition;

@Data
@Domain
public interface BookRevenueDomain {

  @Transition("b845b36a-0d50-498f-b9f5-c223c695397c")
  String title();

  @Transition("e0e85e8f-27e4-4138-a14b-840e2f0c4241")
  Integer revenue();
}
