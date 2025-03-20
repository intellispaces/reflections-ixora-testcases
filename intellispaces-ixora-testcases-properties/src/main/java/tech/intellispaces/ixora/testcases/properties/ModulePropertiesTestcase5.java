package tech.intellispaces.ixora.testcases.properties;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Properties;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

public interface ModulePropertiesTestcase5 {

  /**
   * This testcase demonstrates reading YAML properties.
   * <p>
   * Four units are included to this module.
   * <p>
   * The configuration unit {@link ModuleConfiguration} declares a projection called "ownerAddress". This projection
   * refers to the owner address from module properties.
   * <p>
   * The configuration unit {@link CliConfiguration} declares a projection called "console". This projection refers
   * to the current CLI console of the module.
   * <p>
   * The guide {@link SnakeyamlGuide} implements parsing YAML strings.
   * <p>
   * The guide {@link SimplePropertiesToDataGuide} implements map of properties to data.
   */
  @Module({ ModuleConfiguration.class, CliConfiguration.class, SnakeyamlGuide.class, SimplePropertiesToDataGuide.class })
  abstract class MainUnit {

    /**
     * The implementation of this method will be injected automatically.
     * <p>
     * In this case, this method will return the target of the module's "ownerAddress" projection.
     */
    @Inject
    public abstract Address ownerAddress();

    /**
     * The module startup method.
     * This method will be invoked automatically after the module is started.
     * <p>
     * The values of method arguments will be injected automatically.
     *
     * @param console value of the module projection named 'console'.
     */
    @Startup
    public void startup(@Inject MovableConsole console) {
      console.println("City: " + ownerAddress().city());
      console.println("Street: " + ownerAddress().street());
    }

    /**
     * The main method of the application loads and starts the module.
     */
    public static void main(String[] args) {
      Modules.load(MainUnit.class, args).start();
    }
  }

  @Configuration
  abstract class ModuleConfiguration {
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
    public abstract Address ownerAddress();
  }
}
