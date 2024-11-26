package tech.intellispaces.ixora.samples.http.simple.sample3;

import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.annotation.Get;
import tech.intellispaces.ixora.http.annotation.HttpOntology;
import tech.intellispaces.ixora.http.annotation.QueryParam;

@HttpOntology(port = SimpleHttpPortDomain.class, path = "/")
public interface SimpleHttpPortOntology {

  @Get("date/current")
  HttpResponse currentDate();

  @Get("talk/hello")
  HttpResponse hello(@QueryParam("name") String name);
}
