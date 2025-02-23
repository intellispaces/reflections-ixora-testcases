package tech.intellispaces.ixora.samples.settings;

import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Three units {@link CliConfiguration}, {@link SnakeyamlGuide} and {@link IxoraDictionaryToDataGuide} are included to module.
 * Unit {@link CliConfiguration} defines the projection named 'console' referred to the module CLI console.
 * Unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 * Unit {@link IxoraDictionaryToDataGuide} provides guide to map properties to data.
 */
@Module({
    CliConfiguration.class,
    SnakeyamlGuide.class,
    IxoraDictionaryToDataGuide.class
})
public abstract class YamlSettingsSample2 {

  /**
   * Declare projection to owner address specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Settings("owner.address")
  public abstract AddressHandle ownerAddress();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    console.println("City: " + ownerAddress().city());
    console.println("Street: " + ownerAddress().street());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(YamlSettingsSample2.class, args).start();
  }
}
