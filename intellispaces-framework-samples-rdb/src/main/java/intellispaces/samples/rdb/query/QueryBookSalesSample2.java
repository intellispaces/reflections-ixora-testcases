package intellispaces.samples.rdb.query;

import intellispaces.framework.core.IntellispacesFramework;
import intellispaces.framework.core.annotation.Inject;
import intellispaces.framework.core.annotation.Module;
import intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.Console;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.ResultSet;
import intellispaces.ixora.rdb.Transaction;
import intellispaces.ixora.rdb.TransactionFactory;
import intellispaces.ixora.rdb.annotation.Transactional;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.ixora.structures.association.IxoraPropertiesToDataGuide;
import intellispaces.ixora.structures.collection.List;
import intellispaces.samples.rdb.BookSalesProjection;

@Module(units = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
})
public abstract class QueryBookSalesSample2 {

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract TransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject Console console, @Inject Transaction tx) {
    ResultSet rs = tx.query(QuerySql.BOOK_SALES_SQL);
    List<BookSalesProjection> bookSales = rs.values(BookSalesProjection.class);
    for (BookSalesProjection bookSale : bookSales.nativeList()) {
      console.print("Book title: ");
      console.print(bookSale.title());
      console.print(". Sales: ");
      console.println(bookSale.sales() != null ?  bookSale.sales() : 0);
    }
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(QueryBookSalesSample2.class, args);
  }
}
