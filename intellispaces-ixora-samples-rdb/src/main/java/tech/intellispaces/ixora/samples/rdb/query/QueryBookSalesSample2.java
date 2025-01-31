package tech.intellispaces.ixora.samples.rdb.query;

import tech.intellispaces.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.ixora.data.collection.ListHandle;
import tech.intellispaces.ixora.hikary.HikariConfiguration;
import tech.intellispaces.ixora.rdb.MovableResultSetHandle;
import tech.intellispaces.ixora.rdb.MovableTransactionHandle;
import tech.intellispaces.ixora.rdb.RdbConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.samples.rdb.BookSalesProjectionHandle;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraDictionaryToDataGuide.class
})
public abstract class QueryBookSalesSample2 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableTransactionHandle tx, @Inject MovableConsoleHandle console) {
    MovableResultSetHandle rs = tx.query(Queries.BOOK_SALES_SQL);
    ListHandle<BookSalesProjectionHandle> bookSales = rs.dataValues(BookSalesProjectionHandle.class);
    for (BookSalesProjectionHandle bookSale : bookSales.nativeList()) {
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
    Modules.load(QueryBookSalesSample2.class, args).start();
  }
}
