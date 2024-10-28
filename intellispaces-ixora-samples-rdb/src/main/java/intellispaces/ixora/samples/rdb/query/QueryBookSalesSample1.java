package intellispaces.ixora.samples.rdb.query;

import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.MovableResultSet;
import intellispaces.ixora.rdb.MovableTransaction;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.annotation.Transactional;
import intellispaces.ixora.samples.rdb.BookSalesProjection;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.annotation.Startup;
import intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
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
  public void startup(@Inject MovableTransaction tx, @Inject MovableConsole console) {
    MovableResultSet rs = tx.query(Queries.BOOK_SALES_SQL);
    while (rs.next()) {
      BookSalesProjection bookSales = rs.dataValue(BookSalesProjection.class);
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
    Modules.get(QueryBookSalesSample1.class, args).start();
  }
}
