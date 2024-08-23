package intellispaces.samples.rdb;

import intellispaces.ixora.cli.Console;
import intellispaces.ixora.rdb.ResultSet;
import intellispaces.ixora.rdb.TransactionFactory;
import intellispaces.actions.Action;
import intellispaces.actions.Actions;
import intellispaces.core.IntellispacesFramework;
import intellispaces.core.annotation.Inject;
import intellispaces.core.annotation.Module;
import intellispaces.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.action.TransactionalAction;
import intellispaces.ixora.rdb.Transactions;
import intellispaces.ixora.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;
import intellispaces.ixora.structures.properties.PropertiesToDataIxoraMapper;

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
  public void startup(@Inject TransactionFactory transactionFactory, @Inject Console console) {
    Action mainAction = Actions.get(() -> {
      ResultSet rs = Transactions.current().query("SELECT count(*) as count FROM book.book");
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
