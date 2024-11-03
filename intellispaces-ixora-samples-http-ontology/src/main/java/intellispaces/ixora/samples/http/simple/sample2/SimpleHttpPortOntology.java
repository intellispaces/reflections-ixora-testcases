package intellispaces.ixora.samples.http.simple.sample2;

import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.annotation.Get;
import intellispaces.ixora.http.annotation.HttpOntology;
import intellispaces.ixora.http.annotation.QueryParam;

@HttpOntology(port = SimpleHttpPortDomain.class, path = "/")
public interface SimpleHttpPortOntology {

  @Get("date/current")
  HttpResponse currentDate();

  @Get("welcome/hello")
  HttpResponse hello(@QueryParam("name") String name);
}
