package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;

//@Module(units = CliUnit.class)
public abstract class ModuleProperties4 {

  @Projection
  @Properties("owner.contact")
  public abstract ContactProperties ownerContacts();

  @Startup
  public void startup(ConsoleHandle console, ContactProperties ownerContacts) {
    console.print("Home: ");
    console.println(ownerContacts.home());

    console.print("Office: ");
    console.println(ownerContacts.office());
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties4.class).start(args);
  }
}
