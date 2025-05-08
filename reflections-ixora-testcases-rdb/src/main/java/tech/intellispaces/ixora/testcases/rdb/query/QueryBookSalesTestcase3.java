package tech.intellispaces.ixora.testcases.rdb.query;

import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDataGuide;
import tech.intellispaces.ixora.data.collection.ListHandle;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.statement.MovableResultSetHandle;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionHandle;
import tech.intellispaces.ixora.testcases.rdb.BookSalesProjectionHandle;
import tech.intellispaces.reflections.Jaquarius;
import tech.intellispaces.reflections.annotation.Inject;
import tech.intellispaces.reflections.annotation.Module;
import tech.intellispaces.reflections.annotation.Startup;

import static tech.intellispaces.ixora.testcases.rdb.query.QueryBookSql.SELECT_BOOK_SALES;

/**
 * This testcase demonstrates querying from the database.
 */
@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesSetToDataGuide.class
})
public abstract class QueryBookSalesTestcase3 {

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The method is executed inside a transaction, as the {@link Transactional} annotation is specified.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param tx the current transaction
   * @param console value of the module projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableTransactionHandle tx, @Inject MovableConsole console) {
    MovableResultSetHandle rs = tx.query(SELECT_BOOK_SALES);
    ListHandle<BookSalesProjectionHandle> bookSales = rs.dataList(Types.get(BookSalesProjectionHandle.class));
    for (BookSalesProjectionHandle bookSale : bookSales) {
      console.print("Book title: ");
      console.print(bookSale.title());
      console.print(". Sales: ");
      console.println(bookSale.sales());
    }
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Jaquarius.createModule(QueryBookSalesTestcase3.class, args).start();
  }
}
