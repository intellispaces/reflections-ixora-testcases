package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.core.annotation.Unit;

public interface ModuleProperties5 {

//  @Module(units = { CliUnit.class, ConfigurationUnit.class })
  abstract class MainUnit {
    public abstract ContactProperties ownerContacts();

    @Startup
    public void startup(ConsoleHandle console) {
      console.print("Home: ");
      console.println(ownerContacts().home());

      console.print("Office: ");
      console.println(ownerContacts().office());
    }

    public static void main(String[] args) {
      IntellispacesFramework.loadModule(MainUnit.class).start(args);
    }
  }

  @Unit
  abstract class ConfigurationUnit {
    @Projection
    @Properties("owner.contact")
    public abstract ContactProperties ownerContacts();
  }
}
