package tech.intellispacesframework.samples.helloworld;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Startup;

/**
 * IntelliSpaces framework module.
 * <p>
 * Unit {@link CliUnit} is included to this module. In this unit the projection named 'console' to the CLI console is defined.
 * <p>
 * Abstract methods will be auto generated.
 */
@Module(units = CliUnit.class)
public abstract class ModuleHelloWorld2 {

  /**
   * This method will be return projection named 'console' of the module to CLI console. Value of the this projection is defined in unit {@link CliUnit}.
   * <p>
   * Implementation of this method will be auto generated.
   */
  public abstract ConsoleHandle console();

  /**
   * This method will be invoked automatically after the module is started.
   */
  @Startup
  public void startup() {
    console().println("Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleHelloWorld2.class).run(args);
  }
}
