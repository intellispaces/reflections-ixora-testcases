package tech.intellispaces.samples.rdb;

import intellispaces.ixora.cli.ConsoleHandle;
import intellispaces.ixora.rdb.ResultSetHandle;
import intellispaces.ixora.rdb.TransactionFactoryHandle;
import tech.intellispaces.actions.Action;
import tech.intellispaces.actions.Actions;
import tech.intellispaces.core.IntellispacesFramework;
import tech.intellispaces.core.annotation.Inject;
import tech.intellispaces.core.annotation.Module;
import tech.intellispaces.core.annotation.Startup;
import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.hikary.HikariConfiguration;
import tech.intellispaces.ixora.rdb.RdbConfiguration;
import tech.intellispaces.ixora.rdb.action.TransactionalAction;
import tech.intellispaces.ixora.rdb.Transactions;
import tech.intellispaces.ixora.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;
import tech.intellispaces.ixora.structures.properties.PropertiesToDataIxoraMapper;

@Module(units = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    YamlStringToPropertiesSnakeyamlMapper.class,
    PropertiesToDataIxoraMapper.class
})
public abstract class RowCountSample5 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param transactionFactory transaction factory.
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject TransactionFactoryHandle transactionFactory, @Inject ConsoleHandle console) {
    Action mainAction = Actions.get(() -> {
      ResultSetHandle rs = Transactions.current().query("SELECT count(*) as count FROM book.book");
      rs.next();
      console.print("Number books: ");
      console.println(rs.integerValue("count"));
    });

    var transactionalAction = new TransactionalAction(transactionFactory, mainAction);

    transactionalAction.execute();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(RowCountSample5.class, args);
  }
}
