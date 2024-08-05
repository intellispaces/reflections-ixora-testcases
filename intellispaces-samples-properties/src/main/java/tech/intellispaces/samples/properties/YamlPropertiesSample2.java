package tech.intellispaces.samples.properties;

import intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.core.IntellispacesFramework;
import tech.intellispaces.core.annotation.Inject;
import tech.intellispaces.core.annotation.Module;
import tech.intellispaces.core.annotation.Projection;
import tech.intellispaces.core.annotation.Properties;
import tech.intellispaces.core.annotation.Startup;
import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.snakeyaml.YamlStringToPropertiesSnakeyamlMapper;
import tech.intellispaces.ixora.structures.properties.PropertiesToDataIxoraMapper;
import tech.intellispaces.samples.moduleproperties.AddressHandle;

/**
 * This module demonstrates reading module YAML properties.<p/>
 *
 * Three units {@link CliConfiguration}, {@link YamlStringToPropertiesSnakeyamlMapper} and {@link PropertiesToDataIxoraMapper} are included to module.
 * Unit {@link CliConfiguration} defines the projection named 'console' referred to the module CLI console.
 * Unit {@link YamlStringToPropertiesSnakeyamlMapper} provides guide to load YAML properties.
 * Unit {@link PropertiesToDataIxoraMapper} provides guide to map properties to data.
 */
@Module(units = { CliConfiguration.class, YamlStringToPropertiesSnakeyamlMapper.class, PropertiesToDataIxoraMapper.class })
public abstract class YamlPropertiesSample2 {

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
  public void startup(@Inject ConsoleHandle console) {
    console.println("City: " + ownerAddress().city());
    console.println("Street: " + ownerAddress().street());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(YamlPropertiesSample2.class, args);
  }
}
