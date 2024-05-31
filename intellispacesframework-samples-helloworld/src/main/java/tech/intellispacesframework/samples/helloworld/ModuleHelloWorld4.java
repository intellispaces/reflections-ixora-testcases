package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.space.transition.TransitionFunctions;

/**
 * IntelliSpaces framework module.
 * <p>
 * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
 */
@Module(units = CliUnit.class)
public class ModuleHelloWorld4 {

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of all method arguments will be selected automatically.
   *
   * @param console value of the projection named 'console' of this module to CLI console defined in {@link CliUnit} unit.
   */
  @Startup
  public void startup(ConsoleHandle console) {
    // Get identifier of the transition 'sameConsoleWithLastMessageAndNewLine'
    String tid = TransitionFunctions.getTransitionId(Console.class, Console::sameConsoleWithLastMessageAndNewLine, null);

    // Move CLI console through transition defined by ID tid
    console.moveThru(tid, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld4.class).run(args);
  }
}
