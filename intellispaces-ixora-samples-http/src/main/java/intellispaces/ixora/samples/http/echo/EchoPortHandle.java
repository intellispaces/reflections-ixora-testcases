package intellispaces.ixora.samples.http.echo;

import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.jaquarius.annotation.MapperOfMoving;
import intellispaces.jaquarius.annotation.Mover;
import intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(EchoPortDomain.class)
public abstract class EchoPortHandle implements MovableEchoPort {
  private final MovableInboundHttpPort port;

  public EchoPortHandle(MovableInboundHttpPort port) {
    this.port = port;
  }

  public MovableInboundHttpPort getPort() {
    return port;
  }

  @Mover
  @Override
  public MovableEchoPort open() {
    port.open();
    return this;
  }

  @Mover
  @Override
  public MovableEchoPort close() {
    port.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(HttpRequest request) throws HttpException {
    return port.exchange(request);
  }
}
