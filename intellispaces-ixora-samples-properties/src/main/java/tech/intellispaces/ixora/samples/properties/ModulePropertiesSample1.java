package tech.intellispaces.ixora.samples.properties;

import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.PropertiesHandle;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Properties;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates reading YAML properties.
 * <p>
 * The module includes the configuration unit {@link CliConfiguration}. In this unit, a projection called "console"
 * is added to module. The "console" projection refers to the current CLI console of the module.
 * <p>
 * The guide {@link SnakeyamlGuide} is also included to the module. This guide implements parsing YAML strings.
 */
@Module({ CliConfiguration.class, SnakeyamlGuide.class })
public abstract class ModulePropertiesSample1 {

  /**
   * Declares module projection called 'moduleProperties'.
   * <p>
   * In this case, the method will return the module properties, since the {@link Properties} annotation is specified.
   * By default, properties are read from the 'module.yaml' file.
   * <p>
   * The implementation of this method will be injected automatically.
   */
  @Projection
  @Properties
  public abstract PropertiesHandle moduleProperties();

  /**
   * The module startup method.
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of method arguments will be injected automatically.
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    console.println("City: " + moduleProperties().stringValue("owner.address.city"));
    console.println("Street: " + moduleProperties().stringValue("owner.address.street"));
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    Modules.load(ModulePropertiesSample1.class, args).start();
  }
}
