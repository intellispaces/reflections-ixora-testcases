package intellispaces.samples.properties;

import intellispaces.ixora.cli.Console;
import intellispaces.ixora.structures.properties.Properties;
import intellispaces.core.IntellispacesFramework;
import intellispaces.core.annotation.Inject;
import intellispaces.core.annotation.Module;
import intellispaces.core.annotation.Projection;
import intellispaces.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Two units {@link CliConfiguration} and {@link YamlStringToPropertiesSnakeyamlMapper} are included to module.
 * Unit {@link CliConfiguration} defines the projection with name 'console' and referred to the module CLI console.
 * And unit {@link YamlStringToPropertiesSnakeyamlMapper} provides guide to load YAML properties.
 */
@Module(units = {
    CliConfiguration.class,
    YamlStringToPropertiesSnakeyamlMapper.class
})
public abstract class YamlPropertiesSample1 {

  /**
   * Declare projection to module properties specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @intellispaces.core.annotation.Properties
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
