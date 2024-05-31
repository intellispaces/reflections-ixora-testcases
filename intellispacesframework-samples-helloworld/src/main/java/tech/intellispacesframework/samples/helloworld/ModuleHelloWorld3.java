package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

/**
 * IntelliSpaces framework module.
 * <p>
 * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
 */
@Module(units = CliUnit.class)
public class ModuleHelloWorld3 {

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of all method arguments will be selected automatically.
   *
   * @param console value of the projection named 'console' of this module to CLI console defined in {@link CliUnit} unit.
   */
  @Startup
  public void startup(ConsoleHandle console) {
    // Move CLI console through the transition 'sameConsoleWithLastMessageAndNewLine' with qualifier "Hello, world!"
    console.moveThru(Console::sameConsoleWithLastMessageAndNewLine, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld3.class).run(args);
  }
}
