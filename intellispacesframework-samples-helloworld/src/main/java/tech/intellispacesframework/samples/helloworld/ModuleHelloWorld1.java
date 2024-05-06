package tech.intellispacesframework.samples.helloworld;

import intellispaces.cli.CliUnit;
import intellispaces.cli.ConsoleHandle;

import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

@Module(include = CliUnit.class)
public class ModuleHelloWorld1 {

  public static void main(String[] args) {
    IntellispacesFramework.createModule(ModuleHelloWorld1.class).start();
  }

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("Hello, world!");
  }
}
