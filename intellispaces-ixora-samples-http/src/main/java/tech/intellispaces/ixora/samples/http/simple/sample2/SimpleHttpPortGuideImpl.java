package tech.intellispaces.ixora.samples.http.simple.sample2;

import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.HttpResponses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Guide
public abstract class SimpleHttpPortGuideImpl extends SimpleHttpPortGuide {

  @Override
  public HttpResponseHandle currentDate() {
    return HttpResponses.ok(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }

  @Override
  public HttpResponseHandle hello(String name) {
    return HttpResponses.ok("Hello, " + name + "!");
  }
}
