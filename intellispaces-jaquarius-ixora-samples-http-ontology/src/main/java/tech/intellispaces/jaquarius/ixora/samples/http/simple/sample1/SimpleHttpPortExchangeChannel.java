package tech.intellispaces.jaquarius.ixora.samples.http.simple.sample1;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestDomain;
import tech.intellispaces.jaquarius.ixora.http.HttpResponseDomain;

@Channel("fd0fdb02-f405-4a27-813c-40980f48c55b")
public interface SimpleHttpPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(SimpleHttpPortDomain port, HttpRequestDomain request);
}
