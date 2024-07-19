package tech.intellispaces.framework.samples.properties;

import intellispaces.ixora.cli.ConsoleHandle;
import intellispaces.ixora.structures.properties.PropertiesHandle;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.ixora.commons.cli.CliConfiguration;
import tech.intellispaces.ixora.snakeyaml.YamlStringToPropertiesSnakeyamlGuide;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Two units {@link CliConfiguration} and {@link YamlStringToPropertiesSnakeyamlGuide} are included to module.
 * Unit {@link CliConfiguration} defines the projection with name 'console' and referred to the module CLI console.
 * And unit {@link YamlStringToPropertiesSnakeyamlGuide} provides guide to load YAML properties.
 */
@Module(units = { CliConfiguration.class, YamlStringToPropertiesSnakeyamlGuide.class })
public abstract class YamlPropertiesSample1 {

  /**
   * Declare projection to module properties specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Properties
  public abstract PropertiesHandle moduleProperties();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleHandle console) {
    console.println("City: " + moduleProperties().stringValue("owner.address.city"));
    console.println("Street: " + moduleProperties().stringValue("owner.address.street"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(YamlPropertiesSample1.class).start(args);
  }
}
