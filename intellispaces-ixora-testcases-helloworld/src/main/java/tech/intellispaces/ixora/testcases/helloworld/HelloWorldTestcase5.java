package tech.intellispaces.ixora.testcases.helloworld;

import tech.intellispaces.ixora.cli.ConsoleDomain;
import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.space.channel.ChannelIds;
import tech.intellispaces.jaquarius.system.Modules;

/**
 * This module demonstrates printing a string to the module console.
 * <p>
 * The module includes the configuration unit {@link CliConfiguration}. In this unit, a projection called "console"
 * is added to module. The "console" projection refers to the current CLI console of the module.
 */
@Module(CliConfiguration.class)
public class HelloWorldTestcase5 {

  /**
   * The module startup method.
   * This method will be invoked automatically after the module is started.
   * <p>
   * All arguments of the startup method will be injected automatically.
   * In this case, the value of the namesake projection of the module will be inserted into the "console" parameter.
   * <p>
   * Inside the method, the console moves through the channel referenced by channel identifier (CID) with qualifier "Hello, world!".
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    // Gets identifier of the channel
    String cid = ChannelIds.get(ConsoleDomain.class, ConsoleDomain::println, "");

    // Moves console through channel
    console.moveThru(cid, "Hello, world!");
  }

  /**
   * The main method of the application loads and starts the module.
   */
  public static void main(String[] args) {
    Modules.load(HelloWorldTestcase5.class, args).start();
  }
}
