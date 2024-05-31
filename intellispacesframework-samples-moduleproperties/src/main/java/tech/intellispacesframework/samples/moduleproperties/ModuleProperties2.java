package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;

/**
 * IntelliSpaces framework module.
 * <p>
 * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
 * <p>
 * Abstract methods will be auto generated.
 */
@Module(units = { CliUnit.class, SnakeyamlGuide.class })
public abstract class ModuleProperties2 {

  @Projection
  @Properties("owner.address")
  public abstract tech.intellispaces.ixora.structures.properties.Properties addressProperties();

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of all method arguments will be selected automatically.
   *
   * @param console value of the projection named 'console' of this module to CLI console defined in {@link CliUnit} unit.
   */
  @Startup
  public void startup(ConsoleHandle console) {
    console.println("City: " + addressProperties().stringValue("city"));
    console.println("Street: " + addressProperties().stringValue("street"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties2.class).run(args);
  }
}
