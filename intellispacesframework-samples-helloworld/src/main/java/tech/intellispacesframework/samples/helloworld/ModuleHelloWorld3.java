package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.Console;
import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(units = CliUnit.class)
public class ModuleHelloWorld3 {

  @Startup
  public void startup(ConsoleHandle console) {
    console.moveThru(Console::sameConsoleWithLastMessageAndNewLine, "Hello, world!");
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld3.class).start(args);
  }
}
