package intellispaces.samples.properties;

import intellispaces.framework.core.IntellispacesFramework;
import intellispaces.framework.core.annotation.Inject;
import intellispaces.framework.core.annotation.Module;
import intellispaces.framework.core.annotation.Projection;
import intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.Console;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.ixora.structures.association.Properties;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Two units {@link CliConfiguration} and {@link SnakeyamlGuide} are included to module.
 * Unit {@link CliConfiguration} defines the projection with name 'console' and referred to the module CLI console.
 * And unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 */
@Module(include = {
    CliConfiguration.class,
    SnakeyamlGuide.class
})
public abstract class YamlPropertiesSample1 {

  /**
   * Declare projection to module properties specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @intellispaces.framework.core.annotation.Properties
  public abstract Properties moduleProperties();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject Console console) {
    console.println("City: " + moduleProperties().stringValue("owner.address.city"));
    console.println("Street: " + moduleProperties().stringValue("owner.address.street"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(YamlPropertiesSample1.class, args);
  }
}
