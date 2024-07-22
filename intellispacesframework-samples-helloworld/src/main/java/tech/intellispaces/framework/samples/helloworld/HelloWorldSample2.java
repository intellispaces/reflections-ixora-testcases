package tech.intellispaces.framework.samples.helloworld;

import intellispaces.ixora.mindstructs.cli.ConsoleHandle;
import tech.intellispaces.framework.core.IntellispacesFramework;
import tech.intellispaces.framework.core.annotation.Inject;
import tech.intellispaces.framework.core.annotation.Module;
import tech.intellispaces.framework.core.annotation.Startup;
import tech.mindstructs.cli.CliConfiguration;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliConfiguration} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(units = CliConfiguration.class)
public abstract class HelloWorldSample2 {

  /**
   * This method will be return projection named 'console' of the module to CLI console. Value of this projection is defined in unit {@link CliUnit}.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  public abstract ConsoleHandle console();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   */
  @Startup
  public void startup() {
    console().println("Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    IntellispacesFramework.loadModule(HelloWorldSample2.class, args);
  }
}
