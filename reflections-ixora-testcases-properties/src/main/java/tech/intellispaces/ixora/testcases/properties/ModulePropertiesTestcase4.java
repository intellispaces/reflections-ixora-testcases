package tech.intellispaces.ixora.testcases.properties;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDatasetGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Projection;
import tech.intellispaces.reflections.framework.annotation.Properties;
import tech.intellispaces.reflections.framework.annotation.Startup;

/**
 * This testcase demonstrates reading YAML properties.
 * <p>
 * Three units are included to this module.
 * <p>
 * The configuration unit {@link CliConfiguration} declares a projection called "console". This projection refers to
 * the current CLI console of the module.
 * <p>
 * The guide {@link SnakeyamlGuide} implements parsing YAML strings.
 * <p>
 * The guide {@link SimplePropertiesSetToDatasetGuide} implements map of properties to data.
 */
@Module({ CliConfiguration.class, SnakeyamlGuide.class, SimplePropertiesSetToDatasetGuide.class })
public abstract class ModulePropertiesTestcase4 {

  /**
   * Declares module projection called 'ownerCity'.
   * <p>
   * In this case, the method will return the owner address from module properties,
   * since the {@link Properties} annotation is specified.
   * <p>
   * The implementation of this method will be injected automatically.
   */
  @Projection
  @Properties("owner.address.city")
  public abstract String ownerCity();

  /**
   * Declares module projection called 'ownerStreet'.
   * <p>
   * In this case, the method will return the owner address from module properties,
   * since the {@link Properties} annotation is specified.
   * <p>
   * The implementation of this method will be injected automatically.
   */
  @Projection
  @Properties("owner.address.street")
  public abstract String ownerStreet();

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
    console.println("City: " + ownerCity());
    console.println("Street: " + ownerStreet());
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    ReflectionsFramework.loadModule(ModulePropertiesTestcase4.class, args).start();
  }
}
