package tech.intellispaces.framework.samples.helloworld;

import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.framework.core.space.transition.TransitionFunctions;
import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleMovableHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliUnit} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(units = CliUnit.class)
public class HelloWorldSample4 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleMovableHandle console) {
    // Get identifier of the transition 'Console::println'
    String tid = TransitionFunctions.getTransitionId(Console.class, Console::println, null);

    // Move CLI console through transition defined by ID tid
    console.moveThru(tid, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(HelloWorldSample4.class).run(args);
  }
}
