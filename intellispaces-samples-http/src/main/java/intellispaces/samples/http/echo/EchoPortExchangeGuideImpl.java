package intellispaces.samples.http.echo;

import intellispaces.framework.core.annotation.AutoGuide;
import intellispaces.framework.core.annotation.Guide;
import intellispaces.framework.core.annotation.Mapper;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.HttpResponses;
import intellispaces.ixora.internet.UrlToQueryParamValuesGuide;

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
