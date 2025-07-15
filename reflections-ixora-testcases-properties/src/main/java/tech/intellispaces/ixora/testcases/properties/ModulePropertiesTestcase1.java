package tech.intellispaces.ixora.testcases.properties;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.PropertiesSet;
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
 * The module includes the configuration unit {@link CliConfiguration}. In this unit, a projection called "console"
 * is added to module. The "console" projection refers to the current CLI console of the module.
 * <p>
 * The guide {@link SnakeyamlGuide} is also included to the module. This guide implements parsing YAML strings.
 */
@Module({ CliConfiguration.class, SnakeyamlGuide.class })
public abstract class ModulePropertiesTestcase1 {

  /**
   * Declares module projection called 'moduleProperties'.
   * <p>
   * In this case, the method will return the module properties, since the {@link PropertiesSet} annotation is specified.
   * By default, properties are read from the 'module.yaml' file.
   * <p>
   * The implementation of this method will be injected automatically.
   */
  @Projection
  @Properties
  public abstract PropertiesSet moduleProperties();

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
    console.println("City: " + moduleProperties().stringProperty("owner.address.city"));
    console.println("Street: " + moduleProperties().stringProperty("owner.address.street"));
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    ReflectionsFramework.loadModule(ModulePropertiesTestcase1.class, args).start();
  }
}
