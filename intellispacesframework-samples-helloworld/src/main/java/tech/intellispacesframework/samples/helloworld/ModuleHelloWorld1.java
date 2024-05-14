package tech.intellispacesframework.samples.helloworld;

import intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(include = CliUnit.class)
public class ModuleHelloWorld1 {

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld1.class);
  }

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("Hello, world!");
  }
}
