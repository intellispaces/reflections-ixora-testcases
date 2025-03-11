package tech.intellispaces.ixora.testcases.rdb;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tech.intellispaces.ixora.rdb.annotation.PersistedEntity;
import tech.intellispaces.ixora.rdb.annotation.Projection;
import tech.intellispaces.ixora.rdb.transaction.TransactionalEntityDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;

/**
 * This domain represents the Book Order persisted entity.
 */
@PersistedEntity
@Table(schema = "books", name = "book_order")
@Domain("d278ea68-26a3-4b78-901e-92b6f64d3b15")
public interface OrderDomain extends TransactionalEntityDomain {

  @Id
  @Column(name = "id")
  @Channel("019020aa-06cf-41e3-b378-df0e7ad67728")
  int id();

  @Column(name = "book_id")
  @Channel("b9508e41-2f01-4c56-93b0-5b513e99ec17")
  int bookId();

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  @Channel("db9dc28d-6cfa-4688-879e-8449d9dfa6d3")
  BookDomain book();

  @Column(name = "price")
  @Channel("a9fb5771-8d2c-473d-8129-f7e3a34fa5a4")
  int price();

  @Projection(query = "SELECT COUNT(*) FROM book_order bo WHERE bo.book_id = @bookId")
  @Channel("686b6e22-13a2-45e2-988b-8fb166b77fb2")
  int totalCountOrderForBook();

  @Channel("7d99e151-2493-4114-9a44-4df37db3d63a")
  TransactionalEntityDomain asTransactionalEntity();
}
