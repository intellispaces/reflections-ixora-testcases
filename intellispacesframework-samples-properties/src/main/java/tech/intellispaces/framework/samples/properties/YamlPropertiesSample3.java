package tech.intellispaces.framework.samples.properties;

import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.framework.samples.moduleproperties.AddressHandle;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.commons.structures.properties.PropertiesToDataGuide;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Three units {@link CliUnit}, {@link SnakeyamlGuide} and {@link PropertiesToDataGuide} are included to module.
 * Unit {@link CliUnit} defines the projection named 'console' referred to the module CLI console.
 * Unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 * Unit {@link PropertiesToDataGuide} provides guide to map properties to data.
 */
@Module(units = { CliUnit.class, SnakeyamlGuide.class, PropertiesToDataGuide.class })
public abstract class YamlPropertiesSample3 {

  /**
   * Declare projection to owner address specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Properties("owner.address")
  public abstract AddressHandle ownerAddress();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleHandle console, @Inject AddressHandle ownerAddress) {
    console.println("City: " + ownerAddress.city());
    console.println("Street: " + ownerAddress.street());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(YamlPropertiesSample3.class).run(args);
  }
}
