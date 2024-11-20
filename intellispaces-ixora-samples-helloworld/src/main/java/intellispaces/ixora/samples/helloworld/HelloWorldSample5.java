package intellispaces.ixora.samples.helloworld;

import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.ConsoleDomain;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.jaquarius.annotation.Inject;
import intellispaces.jaquarius.annotation.Module;
import intellispaces.jaquarius.annotation.Startup;
import intellispaces.jaquarius.space.channel.ChannelFunctions;
import intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates the output to the console.<p/>
 *
 * Unit {@link CliConfiguration} is included to this module. In this unit the projection with name 'console' and referred to module CLI console is defined.
 */
@Module(CliConfiguration.class)
public class HelloWorldSample5 {

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    // Get identifier of the channel 'Console::println'
    String cid = ChannelFunctions.getChannelId(ConsoleDomain.class, ConsoleDomain::println, "");

    // Move CLI console through channel defined by channel ID
    console.moveThru(cid, "Hello, world!");
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.get(HelloWorldSample5.class, args).start();
  }
}
