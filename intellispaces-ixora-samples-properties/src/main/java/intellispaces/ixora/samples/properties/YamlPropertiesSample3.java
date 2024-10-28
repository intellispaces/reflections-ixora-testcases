package intellispaces.ixora.samples.properties;

import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import intellispaces.ixora.samples.moduleproperties.Address;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.jaquarius.IntellispacesFramework;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.annotation.Projection;
import intellispaces.jaquarius.annotation.Properties;
import intellispaces.jaquarius.annotation.Startup;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Three units {@link CliConfiguration}, {@link SnakeyamlGuide} and {@link IxoraPropertiesToDataGuide} are included to module.
 * Unit {@link CliConfiguration} defines the projection named 'console' referred to the module CLI console.
 * Unit {@link SnakeyamlGuide} provides guide to load YAML properties.
 * Unit {@link IxoraPropertiesToDataGuide} provides guide to map properties to data.
 */
@Module({
    CliConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class
})
public abstract class YamlPropertiesSample3 {

  /**
   * Declare projection to owner address specified by default in file 'module.yaml'.<p/>
   *
   * This abstract method will be auto implemented in wrapper class.
   */
  @Projection
  @Properties("owner.address")
  public abstract Address ownerAddress();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console, @Inject Address ownerAddress) {
    console.println("City: " + ownerAddress.city());
    console.println("Street: " + ownerAddress.street());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(YamlPropertiesSample3.class, args);
  }
}
