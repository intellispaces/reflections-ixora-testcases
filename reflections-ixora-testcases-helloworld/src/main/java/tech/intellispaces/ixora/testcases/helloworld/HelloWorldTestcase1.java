package tech.intellispaces.ixora.testcases.helloworld;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.reflections.framework.Jaquarius;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Startup;

/**
 * This testcase demonstrates printing a string to the module console.
 * <p>
 * The module includes the configuration unit {@link CliConfiguration}. In this unit, a projection called "console"
 * is added to module. The "console" projection refers to the current CLI console of the module.
 */
@Module(CliConfiguration.class)
public class HelloWorldTestcase1 {

  /**
   * The module startup method.
   * This method will be invoked automatically after the module is started.
   * <p>
   * All arguments of the startup method will be injected automatically.
   * In this case, the value of the namesake projection of the module will be inserted into the "console" parameter.
   * <p>
   * Inside the method, the value of the "console" parameter is used to print the string.
   *
   * @param console value of the module projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    console.println("Hello, world!");
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    Jaquarius.createModule(HelloWorldTestcase1.class, args).start();
  }
}
