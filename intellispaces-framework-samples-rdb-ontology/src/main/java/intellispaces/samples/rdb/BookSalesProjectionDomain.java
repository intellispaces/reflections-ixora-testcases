package intellispaces.samples.rdb;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.ixora.rdb.annotation.Projection;
import jakarta.persistence.Column;

@Projection
@Domain("cf3a572e-b571-46dc-aee6-9cd448d50cc7")
public interface BookSalesProjectionDomain {

  @Column(name = "title")
  @Channel("b845b36a-0d50-498f-b9f5-c223c695397c")
  String title();

  @Column(name = "sales")
  @Channel("e0e85e8f-27e4-4138-a14b-840e2f0c4241")
  Integer sales();
}
