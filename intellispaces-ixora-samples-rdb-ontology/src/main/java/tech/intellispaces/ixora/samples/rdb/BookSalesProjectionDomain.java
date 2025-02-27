package tech.intellispaces.ixora.samples.rdb;

import jakarta.persistence.Column;
import tech.intellispaces.ixora.rdb.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;

@Projection(query = """
      SELECT b.title as title, SUM(bo.price) as sales
      FROM books.book b
      LEFT JOIN books.book_order bo ON bo.book_id = b.id
      GROUP BY b.id, b.title
      ORDER BY b.id
    """)
@Domain("cf3a572e-b571-46dc-aee6-9cd448d50cc7")
public interface BookSalesProjectionDomain {

  @Column(name = "title")
  @Channel("b845b36a-0d50-498f-b9f5-c223c695397c")
  String title();

  @Column(name = "sales")
  @Channel("e0e85e8f-27e4-4138-a14b-840e2f0c4241")
  Integer sales();
}
