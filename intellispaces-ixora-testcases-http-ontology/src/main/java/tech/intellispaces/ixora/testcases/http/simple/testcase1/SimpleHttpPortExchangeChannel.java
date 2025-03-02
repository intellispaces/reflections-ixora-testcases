package tech.intellispaces.ixora.testcases.http.simple.testcase1;

import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.jaquarius.annotation.Channel;

@Channel("fd0fdb02-f405-4a27-813c-40980f48c55b")
public interface SimpleHttpPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(SimpleHttpPortDomain port, HttpRequestDomain request);
}
