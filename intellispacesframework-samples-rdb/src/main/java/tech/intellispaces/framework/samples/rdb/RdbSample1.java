package tech.intellispaces.framework.samples.rdb;

import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.commons.structures.properties.IxoraPropertiesToDataGuide;
import tech.intellispaces.ixora.rdb.ResultSetHandle;
import tech.intellispaces.ixora.rdb.TransactionFactoryHandle;
import tech.intellispaces.ixora.rdb.TransactionFunctions;
import tech.intellispaces.ixora.rdb.hikary.unit.HikariUnit;
import tech.intellispaces.ixora.rdb.unit.RdbUnit;
import tech.intellispaces.ixora.snakeyaml.SnakeYamlStringToPropertiesGuide;

@Module(units = {
    CliUnit.class,
    SnakeYamlStringToPropertiesGuide.class,
    IxoraPropertiesToDataGuide.class,
    RdbUnit.class,
    HikariUnit.class
})
public abstract class RdbSample1 {

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  public abstract TransactionFactoryHandle transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleHandle console) {
    TransactionFactoryHandle transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      ResultSetHandle rs = tx.query("SELECT count(*) as count FROM person");
      console.println("");

    });
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(RdbSample1.class).start(args);
  }
}
