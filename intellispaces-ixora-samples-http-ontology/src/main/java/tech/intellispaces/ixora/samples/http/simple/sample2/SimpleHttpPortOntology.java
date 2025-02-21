package tech.intellispaces.ixora.samples.http.simple.sample2;

import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.ixora.http.annotation.Get;
import tech.intellispaces.ixora.http.annotation.HttpOntology;
import tech.intellispaces.ixora.http.annotation.QueryParam;

@HttpOntology(port = SimpleHttpPortDomain.class, path = "/")
public interface SimpleHttpPortOntology {

  @Get("date/current")
  HttpResponseDomain currentDate();

  @Get("welcome/hello")
  HttpResponseDomain hello(@QueryParam("name") String name);
}
