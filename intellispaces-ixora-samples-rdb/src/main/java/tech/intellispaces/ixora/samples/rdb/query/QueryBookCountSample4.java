package tech.intellispaces.ixora.samples.rdb.query;

import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.statement.MovableResultSetHandle;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactoryHandle;
import tech.intellispaces.ixora.rdb.transaction.TransactionFunctions;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    IxoraDictionaryToDataGuide.class
})
public abstract class QueryBookCountSample4 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param transactionFactory transaction factory.
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableTransactionFactoryHandle transactionFactory, @Inject MovableConsoleHandle console) {
    TransactionFunctions.transactional(transactionFactory, tx -> {
      MovableResultSetHandle rs = tx.query(Queries.BOOK_COUNT);
      rs.next();
      console.print("Number books: ");
      console.println(rs.integer32Value("count"));
    });
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(QueryBookCountSample4.class, args).start();
  }
}
