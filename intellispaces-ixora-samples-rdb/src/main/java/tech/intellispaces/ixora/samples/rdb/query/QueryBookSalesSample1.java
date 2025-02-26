package tech.intellispaces.ixora.samples.rdb.query;

import tech.intellispaces.commons.base.type.Types;
import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.statement.MovableResultSetHandle;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionHandle;
import tech.intellispaces.ixora.samples.rdb.BookSalesProjectionHandle;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesToDataGuide.class
})
public abstract class QueryBookSalesSample1 {

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
    while (rs.next()) {
      BookSalesProjectionHandle bookSales = rs.dataValue(Types.get(BookSalesProjectionHandle.class));
      console.print("Book title: ");
      console.print(bookSales.title());
      console.print(". Sales: ");
      console.println(bookSales.sales() != null ?  bookSales.sales() : 0);
    }
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(QueryBookSalesSample1.class, args).start();
  }
}
