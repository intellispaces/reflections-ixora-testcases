package tech.intellispacesframework.samples.helloworld;

import intellispaces.cli.CliUnit;
import intellispaces.cli.ConsoleHandle;

import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Include;
import tech.intellispacesframework.core.annotation.Startup;

@Include(CliUnit.class)
public class HelloWorld1 {

  public static void main(String[] args) {
    IntellispacesFramework.createSystemModule(HelloWorld1.class).start();
  }

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("Hello, world!");
  }
}
