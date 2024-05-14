package tech.intellispacesframework.samples.helloworld;

import intellispaces.ixora.cli.Console;
import intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(include = CliUnit.class)
public class ModuleHelloWorld3 {

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld3.class);
  }

  @Startup
  public void startup(ConsoleHandle console) throws Exception {
    console.moveThru(Console::sameConsoleWithLastMessageAndNewLine, "Hello, world!");
  }
}
