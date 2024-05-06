package tech.intellispacesframework.samples.helloworld;

import intellispaces.cli.CliUnit;
import intellispaces.cli.ConsoleHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(include = CliUnit.class)
public abstract class ModuleHelloWorld2 {

  public abstract ConsoleHandle console();

  public static void main(String[] args) {
    IntellispacesFramework.createModule(ModuleHelloWorld2.class).start();
  }

  @Startup
  public void startup() {
    console().println("Hello, world!");
  }
}
