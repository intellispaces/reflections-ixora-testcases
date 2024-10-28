package intellispaces.ixora.samples.http.echo;

import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.HttpResponses;
import intellispaces.ixora.internet.UrlToQueryParamValuesGuide;
import intellispaces.jaquarius.annotation.AutoGuide;
import intellispaces.jaquarius.annotation.Guide;
import intellispaces.jaquarius.annotation.Mapper;

@Guide
public abstract class EchoPortExchangeGuideImpl implements EchoPortExchangeGuide {

  @AutoGuide
  abstract UrlToQueryParamValuesGuide urlToQueryParamValuesGuide();

  @Mapper
  @Override
  public HttpResponse exchange(EchoPort port, HttpRequest request) {
    java.util.List<String> messages = urlToQueryParamValuesGuide()
        .urlToQueryParamValues(request.requestURI(), "msg")
        .nativeList();
    return HttpResponses.ok(messages.isEmpty() ? "" : messages.get(0));
  }
}
