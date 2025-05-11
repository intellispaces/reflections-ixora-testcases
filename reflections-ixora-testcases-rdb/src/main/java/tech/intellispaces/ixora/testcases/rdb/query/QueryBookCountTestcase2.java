package tech.intellispaces.ixora.testcases.rdb.query;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.statement.MovableResultSet;
import tech.intellispaces.ixora.rdb.transaction.MovableTransaction;
import tech.intellispaces.reflections.framework.Jaquarius;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Startup;

import static tech.intellispaces.ixora.testcases.rdb.query.QueryBookSql.SELECT_BOOK_COUNT;

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
public abstract class QueryBookCountTestcase2 {

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
  public void startup(@Inject MovableTransaction tx, @Inject MovableConsole console) {
    MovableResultSet rs = tx.query(SELECT_BOOK_COUNT);
    rs.next();
    console.print("Number books: ");
    console.println(rs.integer32Value("count"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Jaquarius.createModule(QueryBookCountTestcase2.class, args).start();
  }
}
