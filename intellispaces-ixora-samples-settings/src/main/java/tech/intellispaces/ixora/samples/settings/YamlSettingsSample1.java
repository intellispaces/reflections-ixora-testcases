package tech.intellispaces.ixora.samples.settings;

import tech.intellispaces.ixora.data.association.Dictionary;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsole;
import tech.intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Two units {@link CliConfiguration} and {@link SnakeyamlGuide} are included to module.
 * Unit {@link CliConfiguration} defines the projection with name 'console' and referred to the module CLI console.
 * And unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 */
@Module({
    CliConfiguration.class,
    SnakeyamlGuide.class
})
public abstract class YamlSettingsSample1 {

  /**
   * Declare projection to module properties specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Settings
  public abstract Dictionary moduleSettings();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    console.println("City: " + moduleSettings().stringValue("owner.address.city"));
    console.println("Street: " + moduleSettings().stringValue("owner.address.street"));
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(YamlSettingsSample1.class, args).start();
  }
}
