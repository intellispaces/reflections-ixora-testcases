package tech.intellispaces.framework.samples.moduleproperties;

import tech.intellispaces.framework.samples.moduleproperties.model.Address;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;

/**
 * IntelliSpaces framework module.
 * <p>
 * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
 * <p>
 * Abstract methods will be auto generated.
 */
//@Module(units = CliUnit.class)
public abstract class ModuleProperties4 {

  @Projection
  @Properties("owner.contact")
  public abstract Address ownerAddress();

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of all method arguments will be selected automatically.
   *
   * @param console value of the projection named 'console' of this module to CLI console defined in {@link CliUnit} unit.
   * @param ownerAddress value of the projection named 'ownerAddress' of this module to owner address defined in method 'ownerAddress'.
   */
  @Startup
  public void startup(ConsoleHandle console, Address ownerAddress) {
    console.println("City: " + ownerAddress.city());
    console.println("Street: " + ownerAddress.street());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties4.class).run(args);
  }
}
