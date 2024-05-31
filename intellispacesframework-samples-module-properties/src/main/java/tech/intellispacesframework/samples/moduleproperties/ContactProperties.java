package tech.intellispacesframework.samples.moduleproperties;

import tech.intellispacesframework.core.annotation.Data;
import tech.intellispacesframework.core.annotation.Domain;

@Data
@Domain
public interface ContactProperties {

  String home();

  String office();
}
