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
import intellispaces.samples.rdb.BookRevenueProjection;

@Module(units = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
})
public abstract class QueryBookRevenueSample1 {

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
    ResultSet rs = tx.query(QuerySql.BOOK_REVENUE_SQL);
    while (rs.next()) {
      BookRevenueProjection bookRevenue = rs.value(BookRevenueProjection.class);
      console.print("Book title: ");
      console.print(bookRevenue.title());
      console.print(". Revenue: ");
      console.println(bookRevenue.revenue() != null ?  bookRevenue.revenue() : 0);
    }
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(QueryBookRevenueSample1.class, args);
  }
}
