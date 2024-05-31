package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;

@Module(units = { CliUnit.class, SnakeyamlGuide.class })
public abstract class ModuleProperties2 {

  @Projection
  @Properties("owner.address")
  public abstract tech.intellispaces.ixora.structures.properties.Properties addressProperties();

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("City: " + addressProperties().stringValue("city"));
    console.println("Street: " + addressProperties().stringValue("street"));
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties2.class).start(args);
  }
}
