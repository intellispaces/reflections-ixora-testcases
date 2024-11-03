package intellispaces.ixora.samples.http.simple.sample2;

import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.HttpResponses;
import intellispaces.jaquarius.annotation.Guide;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Guide
public abstract class SimpleHttpPortGuideImpl extends SimpleHttpPortGuide {

  @Override
  public HttpResponse currentDate() {
    return HttpResponses.ok(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }

  @Override
  public HttpResponse hello(String name) {
    return HttpResponses.ok("Hello, " + name + "!");
  }
}
