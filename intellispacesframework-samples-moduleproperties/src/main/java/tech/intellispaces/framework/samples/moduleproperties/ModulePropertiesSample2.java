package tech.intellispaces.framework.samples.moduleproperties;

import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;

/**
 * This module demonstrates reading YAML properties.<p/>
 *
 * Two units {@link CliUnit} and {@link SnakeyamlGuide} are included to module.
 * Unit {@link CliUnit} defines the projection with name 'console' and referred to the module CLI console.
 * And unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 */
@Module(units = { CliUnit.class, SnakeyamlGuide.class })
public abstract class ModulePropertiesSample2 {

  /**
   * Declare projection to owner address properties specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Properties("owner.address")
  public abstract tech.intellispaces.ixora.structures.properties.Properties addressProperties();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleHandle console) {
    console.println("City: " + addressProperties().stringValue("city"));
    console.println("Street: " + addressProperties().stringValue("street"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModulePropertiesSample2.class).run(args);
  }
}
