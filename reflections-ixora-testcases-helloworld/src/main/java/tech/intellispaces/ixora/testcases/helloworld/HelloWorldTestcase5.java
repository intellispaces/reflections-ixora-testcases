package tech.intellispaces.ixora.testcases.helloworld;

import tech.intellispaces.ixora.cli.ConsoleDomain;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Startup;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;

/**
 * This testcase demonstrates printing a string to the module console.
 * <p>
 * The module includes the configuration unit {@link CliConfiguration}. In this unit, a projection called "console"
 * is added to module. The "console" projection refers to the current CLI console of the module.
 */
@Module(CliConfiguration.class)
public class HelloWorldTestcase5 {

  /**
   * The module startup method.
   * This method will be invoked automatically after the module is started.
   * <p>
   * All arguments of the startup method will be injected automatically.
   * In this case, the value of the namesake projection of the module will be inserted into the "console" parameter.
   * <p>
   * Inside the method, the console moves through the channel referenced by the method reference {@link ConsoleDomain#println(String)}
   * with qualifier "Hello, world!".
   *
   * @param console value of the module projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableReflection console) {
    console.moveThru(ConsoleDomain.class, ConsoleDomain::println, "Hello, world!");
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    ReflectionsFramework.loadModule(HelloWorldTestcase5.class, args).start();
  }
}
