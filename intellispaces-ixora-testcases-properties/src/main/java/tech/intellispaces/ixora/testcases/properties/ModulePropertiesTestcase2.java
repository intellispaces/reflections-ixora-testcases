package tech.intellispaces.ixora.testcases.properties;

import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesToDataGuide;
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
 * Three units are included to this module.
 * <p>
 * The configuration unit {@link CliConfiguration} declares a projection called "console". This projection refers to
 * the current CLI console of the module.
 * <p>
 * The guide {@link SnakeyamlGuide} implements parsing YAML strings.
 * <p>
 * The guide {@link SimplePropertiesToDataGuide} implements map of properties to data.
 */
@Module({ CliConfiguration.class, SnakeyamlGuide.class, SimplePropertiesToDataGuide.class })
public abstract class ModulePropertiesTestcase2 {

  /**
   * Declares module projection called 'ownerAddress'.
   * <p>
   * In this case, the method will return the owner address from module properties,
   * since the {@link Properties} annotation is specified.
   * <p>
   * The implementation of this method will be injected automatically.
   */
  @Projection
  @Properties("owner.address")
  public abstract AddressHandle ownerAddress();

  /**
   * The module startup method.
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of method arguments will be injected automatically.
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    console.println("City: " + ownerAddress().city());
    console.println("Street: " + ownerAddress().street());
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    Modules.load(ModulePropertiesTestcase2.class, args).start();
  }
}
