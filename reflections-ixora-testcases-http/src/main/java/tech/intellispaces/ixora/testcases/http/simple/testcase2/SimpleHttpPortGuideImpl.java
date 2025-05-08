package tech.intellispaces.ixora.testcases.http.simple.testcase2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.HttpResponses;
import tech.intellispaces.reflections.annotation.Guide;

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
