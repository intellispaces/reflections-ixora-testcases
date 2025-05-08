package tech.intellispaces.ixora.testcases.rdb.query;

public interface QueryBookSql {

  String SELECT_BOOK_COUNT = "SELECT count(*) as count FROM books.book";

  String SELECT_BOOK_SALES = """
    SELECT b.title as title, COALESCE(SUM(bo.price) , 0) as sales
    FROM books.book b
    LEFT JOIN books.book_order bo ON bo.book_id = b.id
    GROUP BY b.id, b.title
    ORDER BY b.id
  """;
}
