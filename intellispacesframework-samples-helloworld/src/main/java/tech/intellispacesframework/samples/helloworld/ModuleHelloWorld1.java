package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(units = CliUnit.class)
public class ModuleHelloWorld1 {

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("Hello, world!");
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld1.class).start(args);
  }
}
