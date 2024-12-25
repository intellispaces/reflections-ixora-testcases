package tech.intellispaces.ixora.samples.rdb.query;

import tech.intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import tech.intellispaces.ixora.hikary.HikariConfiguration;
import tech.intellispaces.ixora.rdb.MovableResultSet;
import tech.intellispaces.ixora.rdb.MovableTransaction;
import tech.intellispaces.ixora.rdb.RdbConfiguration;
import tech.intellispaces.ixora.rdb.Transactions;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsole;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
})
public abstract class QueryBookCountSample1 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableConsole console) {
    MovableTransaction tx = Transactions.current();
    MovableResultSet rs = tx.query(Queries.BOOK_COUNT);
    rs.next();
    console.print("Number books: ");
    console.println(rs.integerValue("count"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(QueryBookCountSample1.class, args).start();
  }
}
