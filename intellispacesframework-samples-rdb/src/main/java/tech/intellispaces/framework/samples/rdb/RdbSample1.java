package tech.intellispaces.framework.samples.rdb;

import intellispaces.ixora.mindstructs.cli.ConsoleHandle;
import intellispaces.ixora.mindstructs.rdb.ResultSetHandle;
import intellispaces.ixora.mindstructs.rdb.TransactionFactoryHandle;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.mindstructs.cli.CliConfiguration;
import tech.mindstructs.rdb.RdbConfiguration;
import tech.mindstructs.rdb.TransactionFunctions;
import tech.mindstructs.hikary.HikariConfiguration;
import tech.mindstructs.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;
import tech.mindstructs.structures.properties.MindstructsPropertiesToDataMapper;

@Module(units = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    YamlStringToPropertiesSnakeyamlMapper.class,
    MindstructsPropertiesToDataMapper.class
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
    IntellispacesFramework.loadModule(RdbSample1.class, args);
  }
}
