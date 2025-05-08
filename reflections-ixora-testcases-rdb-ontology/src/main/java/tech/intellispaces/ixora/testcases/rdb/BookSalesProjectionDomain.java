package tech.intellispaces.ixora.testcases.rdb;

import jakarta.persistence.Column;

import tech.intellispaces.ixora.rdb.annotation.Projection;
import tech.intellispaces.reflections.annotation.Channel;
import tech.intellispaces.reflections.annotation.Domain;

/**
 * This domain represents the book sales projection.
 */
@Projection(query = """
      SELECT b.title as title, SUM(bo.price) as sales
      FROM books.book b
      LEFT JOIN books.book_order bo ON bo.book_id = b.id
      GROUP BY b.id, b.title
      ORDER BY b.id
    """)
@Domain("cf3a572e-b571-46dc-aee6-9cd448d50cc7")
public interface BookSalesProjectionDomain {

  /**
   * The book title.
   */
  @Column(name = "title")
  @Channel("b845b36a-0d50-498f-b9f5-c223c695397c")
  String title();

  /**
   * The book sales.
   */
  @Column(name = "sales")
  @Channel("e0e85e8f-27e4-4138-a14b-840e2f0c4241")
  int sales();
}
