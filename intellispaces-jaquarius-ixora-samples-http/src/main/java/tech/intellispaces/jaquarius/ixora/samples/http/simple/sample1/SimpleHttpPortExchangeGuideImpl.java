package tech.intellispaces.jaquarius.ixora.samples.http.simple.sample1;

import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpResponseHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpResponses;
import tech.intellispaces.jaquarius.ixora.internet.uri.GetUriQueryParamGuide;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Guide
public abstract class SimpleHttpPortExchangeGuideImpl implements SimpleHttpPortExchangeGuide {

  @AutoGuide
  abstract GetUriQueryParamGuide getUriQueryParamGuide();

  @Mapper
  @Override
  public HttpResponseHandle exchange(SimpleHttpPortHandle port, HttpRequestHandle request) {
    String path = request.requestURI().path();

    HttpResponseHandle response;
    switch (path) {
      case "/date/current":
        response = HttpResponses.ok(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        break;
      case "/welcome/hello":
        String name = getUriQueryParamGuide().map(request.requestURI(), "name").get(0);
        response = HttpResponses.ok("Hello, " + name + "!");
        break;
      default:
        response = HttpResponses.notFound();
    }
    return response;
  }
}
