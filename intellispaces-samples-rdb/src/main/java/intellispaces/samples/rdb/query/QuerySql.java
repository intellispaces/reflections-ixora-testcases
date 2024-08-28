package intellispaces.samples.rdb.query;

public interface QuerySql {

  String BOOK_COUNT = "SELECT count(*) as count FROM book.book";

  String BOOK_REVENUE_SQL = """
    SELECT b.title as title, SUM(bo.revenue) as revenue
    FROM book.book b
    LEFT JOIN book.book_order bo ON bo.book_id = b.id
    GROUP BY b.id, b.title
    ORDER BY b.id
  """;
}
