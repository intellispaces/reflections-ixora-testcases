package tech.intellispaces.framework.samples.properties;

import intellispaces.ixora.mindstructs.cli.ConsoleHandle;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Projection;
import tech.intellispaces.framework.core.annotation.Properties;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.framework.samples.moduleproperties.AddressHandle;
import tech.mindstructs.cli.CliConfiguration;
import tech.mindstructs.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;
import tech.mindstructs.structures.properties.MindstructsPropertiesToDataMapper;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Three units {@link CliConfiguration}, {@link YamlStringToPropertiesSnakeyamlMapper} and {@link MindstructsPropertiesToDataMapper} are included to module.
 * Unit {@link CliConfiguration} defines the projection named 'console' referred to the module CLI console.
 * Unit {@link YamlStringToPropertiesSnakeyamlMapper} provides guide to load YAML properties.
 * Unit {@link MindstructsPropertiesToDataMapper} provides guide to map properties to data.
 */
@Module(units = { CliConfiguration.class, YamlStringToPropertiesSnakeyamlMapper.class, MindstructsPropertiesToDataMapper.class })
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
    IntellispacesFramework.loadModule(YamlPropertiesSample3.class, args);
  }
}
