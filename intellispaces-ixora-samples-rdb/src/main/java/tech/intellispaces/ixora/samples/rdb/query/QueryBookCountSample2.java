package tech.intellispaces.ixora.samples.rdb.query;

import tech.intellispaces.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.ixora.hikary.HikariConfiguration;
import tech.intellispaces.ixora.rdb.MovableResultSet;
import tech.intellispaces.ixora.rdb.MovableTransaction;
import tech.intellispaces.ixora.rdb.RdbConfiguration;
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
    IxoraDictionaryToDataGuide.class
})
public abstract class QueryBookCountSample2 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param tx current transaction.
   * @param console value of the projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableTransaction tx, @Inject MovableConsole console) {
    MovableResultSet rs = tx.query(Queries.BOOK_COUNT);
    rs.next();
    console.print("Number books: ");
    console.println(rs.integerValue("count"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(QueryBookCountSample2.class, args).start();
  }
}
