package intellispaces.samples.helloworld;

import intellispaces.framework.core.IntellispacesFramework;
import intellispaces.framework.core.annotation.Inject;
import intellispaces.framework.core.annotation.Module;
import intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.MovableConsole;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliConfiguration} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(include = CliConfiguration.class)
public abstract class HelloWorldSample2 {

  /**
   * This method will be return projection named 'console' of the module to CLI console. Value of this projection is defined in unit {@link CliUnit}.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableConsole console();

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
