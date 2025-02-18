package tech.intellispaces.jaquarius.ixora.samples.http.simple.sample3;

import tech.intellispaces.jaquarius.ixora.http.HttpResponseDomain;
import tech.intellispaces.jaquarius.ixora.http.annotation.Get;
import tech.intellispaces.jaquarius.ixora.http.annotation.HttpOntology;
import tech.intellispaces.jaquarius.ixora.http.annotation.QueryParam;

@HttpOntology(port = SimpleHttpPortDomain.class, path = "/")
public interface SimpleHttpPortOntology {

  @Get("date/current")
  HttpResponseDomain currentDate();

  @Get("talk/hello")
  HttpResponseDomain hello(@QueryParam("name") String name);
}
