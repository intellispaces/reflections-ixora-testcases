package intellispaces.ixora.samples.http.echo;

import intellispaces.ixora.http.HttpPortExchangeChannel;
import intellispaces.ixora.http.HttpRequestDomain;
import intellispaces.ixora.http.HttpResponseDomain;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.jaquarius.annotation.Channel;

@Channel("fd0fdb02-f405-4a27-813c-40980f48c55b")
public interface EchoPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(EchoPortDomain source, HttpRequestDomain request) throws HttpException;
}
