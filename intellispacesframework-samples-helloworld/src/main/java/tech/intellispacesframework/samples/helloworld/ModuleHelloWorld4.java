package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.space.transition.TransitionFunctions;

@Module(units = CliUnit.class)
public class ModuleHelloWorld4 {

  @Startup
  public void startup(ConsoleHandle console) {
    String tid = TransitionFunctions.getTransitionId(Console.class, Console::sameConsoleWithLastMessageAndNewLine, null);
    console.moveThru(tid, "Hello, world!");
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld4.class).start(args);
  }
}
