package tech.intellispacesframework.samples.helloworld;

import intellispaces.ixora.cli.Console;
import intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.space.transition.TransitionFunctions;

@Module(include = CliUnit.class)
public class ModuleHelloWorld4 {

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld4.class);
  }

  @Startup
  public void startup(ConsoleHandle console) throws Exception {
    String tid = TransitionFunctions.getTransitionId(Console.class, Console::sameConsoleWithLastMessageAndNewLine, null);
    console.moveThru(tid, "Hello, world!");
  }
}
