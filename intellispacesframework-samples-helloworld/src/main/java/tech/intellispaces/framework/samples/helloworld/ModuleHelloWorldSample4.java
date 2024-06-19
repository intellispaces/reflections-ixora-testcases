package tech.intellispaces.framework.samples.helloworld;

import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.intellispaces.framework.core.space.transition.TransitionFunctions;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliUnit} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(units = CliUnit.class)
public class ModuleHelloWorldSample4 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject ConsoleHandle console) {
    // Get identifier of the transition 'sameConsoleWithLastMessageAndNewLine'
    String tid = TransitionFunctions.getTransitionId(Console.class, Console::sameConsoleWithLastMessageAndNewLine, null);

    // Move CLI console through transition defined by ID tid
    console.moveThru(tid, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorldSample4.class).run(args);
  }
}
