package tech.intellispaces.framework.samples.moduleproperties;

import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.samples.moduleproperties.model.AddressHandle;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.framework.core.annotation.Unit;
import tech.intellispaces.ixora.commons.structures.properties.PropertiesToDataGuide;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;

public interface ModuleYamlPropertiesSample5 {

  /**
   * This module demonstrates reading module YAML properties.<p/>
   *
   * Three units {@link CliUnit}, {@link SnakeyamlGuide} and {@link PropertiesToDataGuide} are included to module.
   * Unit {@link CliUnit} defines the projection named 'console' referred to the module CLI console.
   * Unit {@link SnakeyamlGuide} provides guide to load YAML properties.
   * Unit {@link PropertiesToDataGuide} provides guide to map properties to data.
   */
  @Module(units = { PropertiesUnit.class, CliUnit.class, SnakeyamlGuide.class, PropertiesToDataGuide.class })
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
    public void startup(@Inject ConsoleHandle console) {
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
  abstract class PropertiesUnit {
    /**
     * Declare projection to owner address specified by default in file 'module.yaml'.<p/>
     *
     * This abstract method will be auto implemented in wrapper class.
     */
    @Projection
    @Properties("owner.address")
    public abstract AddressHandle ownerAddress();
  }
}
