package tech.intellispaces.framework.samples.rdb;

import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.configuration.CliConfiguration;
import tech.intellispaces.ixora.commons.structures.properties.IxoraPropertiesToDataGuide;
import intellispaces.ixora.rdb.ResultSetHandle;
import intellispaces.ixora.rdb.TransactionFactoryHandle;
import tech.intellispaces.ixora.rdb.TransactionFunctions;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.hikary.configuration.HikariConfiguration;
import tech.intellispaces.ixora.snakeyaml.SnakeYamlStringToPropertiesGuide;

@Module(units = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeYamlStringToPropertiesGuide.class,
    IxoraPropertiesToDataGuide.class
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
