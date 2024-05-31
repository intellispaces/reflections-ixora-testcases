package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispaces.ixora.cli.ConsoleHandle;
import tech.intellispacesframework.core.IntellispacesFramework;
import tech.intellispacesframework.core.annotation.Projection;
import tech.intellispacesframework.core.annotation.Properties;
import tech.intellispacesframework.core.annotation.Startup;
import tech.intellispacesframework.samples.moduleproperties.model.Address;

//@Module(units = CliUnit.class)
public abstract class ModuleProperties3 {

  @Projection
  @Properties("owner.address")
  public abstract Address ownerAddress();

  @Startup
  public void startup(ConsoleHandle console) {
    console.println("City: " + ownerAddress().city());
    console.println("Street: " + ownerAddress().street());
  }

  public static void main(String[] args) {
    IntellispacesFramework.loadModule(ModuleProperties3.class).start(args);
  }
}
