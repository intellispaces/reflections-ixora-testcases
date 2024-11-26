package tech.intellispaces.ixora.samples.http.simple.sample1;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.HttpResponses;
import tech.intellispaces.ixora.internet.UriToQueryParamGuide;
import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Guide
public abstract class SimpleHttpPortExchangeGuideImpl implements SimpleHttpPortExchangeGuide {

  @AutoGuide
  abstract UriToQueryParamGuide uriToQueryParamGuide();

  @Mapper
  @Override
  public HttpResponse exchange(SimpleHttpPort port, HttpRequest request) {
    String path = request.requestURI().path();

    HttpResponse response;
    switch (path) {
      case "/date/current":
        response = HttpResponses.ok(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        break;
      case "/welcome/hello":
        String name = uriToQueryParamGuide().map(request.requestURI(), "name").get(0);
        response = HttpResponses.ok("Hello, " + name + "!");
        break;
      default:
        response = HttpResponses.notFound();
    }
    return response;
  }
}
