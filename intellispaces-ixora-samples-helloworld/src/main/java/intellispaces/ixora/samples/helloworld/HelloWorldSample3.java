package intellispaces.ixora.samples.helloworld;

import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.ConsolePrintlnStringChannel;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.annotation.Startup;
import intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliConfiguration} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(CliConfiguration.class)
public class HelloWorldSample3 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    // Move CLI console through the channel 'ConsolePrintlnStringChannel' with qualifier "Hello, world!"
    console.moveThru(ConsolePrintlnStringChannel.class, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.get(HelloWorldSample3.class, args).start();
  }
}
