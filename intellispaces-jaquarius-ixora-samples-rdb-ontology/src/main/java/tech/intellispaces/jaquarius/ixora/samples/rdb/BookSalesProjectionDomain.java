package tech.intellispaces.jaquarius.ixora.samples.rdb;

import jakarta.persistence.Column;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.ixora.rdb.annotation.Projection;

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
