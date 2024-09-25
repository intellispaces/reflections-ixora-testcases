package intellispaces.samples.rdb.query;

public interface Queries {

  String BOOK_COUNT = "SELECT count(*) as count FROM book.book";

  String BOOK_SALES_SQL = """
    SELECT b.title as title, SUM(bo.price) as sales
    FROM book.book b
    LEFT JOIN book.book_order bo ON bo.book_id = b.id
    GROUP BY b.id, b.title
    ORDER BY b.id
  """;
}
