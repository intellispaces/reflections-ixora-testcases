package intellispaces.samples.http.echo;

import intellispaces.ixora.http.MovableInboundHttpPort;

public interface EchoPorts {

  static MovableEchoPort get(MovableInboundHttpPort port) {
    return new EchoPortHandleImpl(port);
  }
}
