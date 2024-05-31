package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.annotation.Unit;
import tech.intellispacesframework.samples.moduleproperties.model.Address;

public interface ModuleProperties5 {

  /**
   * IntelliSpaces framework module.
   * <p>
   * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
   * <p>
   * Abstract methods will be auto generated.
   */
//  @Module(units = { CliUnit.class, ConfigurationUnit.class })
  abstract class MainUnit {
    public abstract Address ownerAddress();

    /**
     * This method will be invoked automatically after the module is started.
     * <p>
     * The values of all method arguments will be selected automatically.
     *
     * @param console value of the projection named 'console' of this module to CLI console defined in {@link CliUnit} unit.
     */
    @Startup
    public void startup(ConsoleHandle console) {
      console.println("City: " + ownerAddress().city());
      console.println("Street: " + ownerAddress().street());
    }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
    public static void main(String[] args) {
      IntellispacesFramework.loadModule(MainUnit.class).run(args);
    }
  }

  @Unit
  abstract class ConfigurationUnit {
    @Projection
    @Properties("owner.contact")
    public abstract Address ownerAddress();
  }
}
