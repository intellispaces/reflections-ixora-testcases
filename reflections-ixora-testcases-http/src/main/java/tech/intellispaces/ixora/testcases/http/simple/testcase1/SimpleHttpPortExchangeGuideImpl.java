package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.HttpResponses;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuide;
import tech.intellispaces.reflections.framework.annotation.AutoGuide;
import tech.intellispaces.reflections.framework.annotation.Guide;
import tech.intellispaces.reflections.framework.annotation.Mapper;

@Guide
public abstract class SimpleHttpPortExchangeGuideImpl implements SimpleHttpPortExchangeGuide {

  @AutoGuide
  abstract GetUriQueryParamGuide getUriQueryParamGuide();

  @Mapper
  @Override
  public HttpResponseHandle exchange(SimpleHttpPort port, HttpRequest request) {
    String path = request.requestURI().path();
    return switch (path) {
      case "/date/current" -> HttpResponses.ok(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
      case "/welcome/hello" -> {
        String name = getUriQueryParamGuide().map(request.requestURI(), "name").get(0);
        yield HttpResponses.ok("Hello, " + name + "!");
      }
      default -> HttpResponses.notFound();
    };
  }
}

