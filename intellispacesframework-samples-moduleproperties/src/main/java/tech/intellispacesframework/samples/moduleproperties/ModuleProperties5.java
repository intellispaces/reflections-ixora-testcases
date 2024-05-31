package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.annotation.Unit;
import tech.intellispacesframework.samples.moduleproperties.model.Address;

public interface ModuleProperties5 {

//  @Module(units = { CliUnit.class, ConfigurationUnit.class })
  abstract class MainUnit {
    public abstract Address ownerAddress();

    @Startup
    public void startup(ConsoleHandle console) {
      console.println("City: " + ownerAddress().city());
      console.println("Street: " + ownerAddress().street());
    }

    public static void main(String[] args) {
      IntellispacesFramework.loadModule(MainUnit.class).start(args);
    }
  }

  @Unit
  abstract class ConfigurationUnit {
    @Projection
    @Properties("owner.contact")
    public abstract Address ownerAddress();
  }
}
