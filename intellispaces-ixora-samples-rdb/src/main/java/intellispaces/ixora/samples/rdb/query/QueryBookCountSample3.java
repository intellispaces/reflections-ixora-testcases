package intellispaces.ixora.samples.rdb.query;

import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.MovableResultSet;
import intellispaces.ixora.rdb.MovableTransactionFactory;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.TransactionFunctions;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.jaquarius.IntellispacesFramework;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.annotation.Startup;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
})
public abstract class QueryBookCountSample3 {

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableTransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    MovableTransactionFactory transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      MovableResultSet rs = tx.query(Queries.BOOK_COUNT);
      rs.next();
      console.print("Number books: ");
      console.println(rs.integerValue("count"));
    });
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(QueryBookCountSample3.class, args);
  }
}
