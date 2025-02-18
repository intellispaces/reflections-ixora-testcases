package tech.intellispaces.jaquarius.ixora.samples.settings;

import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.jaquarius.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.jaquarius.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.system.Modules;

public interface YamlSettingsSample5 {

  /**
   * This module demonstrates reading module YAML properties.<p/>
   *
   * Three units {@link CliConfiguration}, {@link SnakeyamlGuide} and {@link IxoraDictionaryToDataGuide} are included to module.
   * Unit {@link CliConfiguration} defines the projection named 'console' referred to the module CLI console.
   * Unit {@link SnakeyamlGuide} provides guide to load YAML properties.
   * Unit {@link IxoraDictionaryToDataGuide} provides guide to map properties to data.
   */
  @Module({
      ModuleConfiguration.class,
      CliConfiguration.class,
      SnakeyamlGuide.class,
      IxoraDictionaryToDataGuide.class
  })
  abstract class MainUnit {

    /**
     * Inject projection to owner address.<p/>
     *
     * This abstract method will be auto implemented in wrapper class.
     */
    @Inject
    public abstract AddressHandle ownerAddress();

    /**
     * This method will be invoked automatically after the module is started.<p/>
     *
     * The values of all method arguments will be injected automatically.
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
      Modules.load(MainUnit.class, args).start();
    }
  }

  @Configuration
  abstract class ModuleConfiguration {
    /**
     * Declare projection to owner address specified by default in file 'module.yaml'.<p/>
     *
     * This abstract method will be auto implemented in wrapper class.
     */
    @Projection
    @Settings("owner.address")
    public abstract AddressHandle ownerAddress();
  }
}
