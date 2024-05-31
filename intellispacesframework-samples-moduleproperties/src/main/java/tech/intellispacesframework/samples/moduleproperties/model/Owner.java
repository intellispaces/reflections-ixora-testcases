package tech.intellispacesframework.samples.moduleproperties.model;

import tech.intellispacesframework.core.annotation.Data;
import tech.intellispacesframework.core.annotation.Domain;

@Data
@Domain
public interface Owner {

  String name();

  Contact contact();

  Address address();
}
