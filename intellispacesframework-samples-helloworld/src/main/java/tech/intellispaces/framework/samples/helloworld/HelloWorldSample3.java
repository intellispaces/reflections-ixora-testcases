package tech.intellispaces.framework.samples.helloworld;

import intellispaces.ixora.mindstructs.cli.Console;
import intellispaces.ixora.mindstructs.cli.MovableConsoleHandle;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.mindstructs.cli.CliConfiguration;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliConfiguration} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(units = CliConfiguration.class)
public class HelloWorldSample3 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    // Move CLI console through the transition 'Console::println' with qualifier "Hello, world!"
    console.moveThru(Console::println, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(HelloWorldSample3.class, args);
  }
}
