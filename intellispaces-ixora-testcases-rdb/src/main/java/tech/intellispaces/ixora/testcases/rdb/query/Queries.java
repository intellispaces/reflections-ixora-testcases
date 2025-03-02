package tech.intellispaces.ixora.testcases.rdb.query;

public interface Queries {

  String BOOK_COUNT = "SELECT count(*) as count FROM books.book";

  String BOOK_SALES_SQL = """
    SELECT b.title as title, SUM(bo.price) as sales
    FROM books.book b
    LEFT JOIN books.book_order bo ON bo.book_id = b.id
    GROUP BY b.id, b.title
    ORDER BY b.id
  """;
}
