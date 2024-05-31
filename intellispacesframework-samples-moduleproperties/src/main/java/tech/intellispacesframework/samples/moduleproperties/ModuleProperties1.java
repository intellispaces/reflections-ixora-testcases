package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispaces.ixora.commons.cli.CliUnit;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.structures.properties.PropertiesHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Module;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;

@Module(units = { CliUnit.class, SnakeyamlGuide.class })
public abstract class ModuleProperties1 {

  @Projection
  @Properties
  public abstract PropertiesHandle moduleProperties();

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("City: " + moduleProperties().stringValue("owner.address.city"));
    console.println("Street: " + moduleProperties().stringValue("owner.address.street"));
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties1.class).start(args);
  }
}
