package com.pako.sip.client.connection

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef}
import akka.io.{IO, UdpConnected}
import akka.util.ByteString

object SipMessage {
  val Msg = """REGISTER sip:localhost SIP/2.0
              |Via: SIP/2.0/UDP 192.168.1.133:56987;rport;branch=z9hG4bKPjr93HakjIs3g4Y05Y4PKalprVX6HwtnYH
              |Max-Forwards: 70
              |From: "uno11" <sip:uno11@localhost>;tag=Oy0lCIkXgZzADgmqOrgBzSaSih1aHFtT
              |To: "uno11" <sip:uno11@localhost>
              |Call-ID: coftbWPk2CGTZCf4hyhKsQP0qBucnebb
              |CSeq: 5854 REGISTER
              |User-Agent: Telephone 1.2.6
              |Contact: "uno11" <sip:uno11@192.168.1.133:56987;ob>
              |Expires: 300
              |Authorization: Digest username="uno", realm="asterisk" nonce="6710b762", uri="sip:localhost", response="ade01c5ce374f3e6ee01405e429be6", algorithm=MD5
              |Content-Length:  0
              |
              |
            """.stripMargin
}

/**
  * Created by pako on 21/6/17.
  */
class UdpActor(remote: InetSocketAddress) extends Actor {
  import context.system
  IO(UdpConnected) ! UdpConnected.Connect(self, remote)

  def receive = {
    case UdpConnected.Connected =>
      context.become(ready(sender()))
  }

  def ready(connection: ActorRef): Receive = {
    case UdpConnected.Received(data) =>
    // process data, send it on, etc.
      println("*********** received ***********\n" + data.decodeString("UTF-8"))
    case msg: String =>
      connection ! UdpConnected.Send(ByteString(msg))
    case UdpConnected.Disconnect =>
      connection ! UdpConnected.Disconnect
    case UdpConnected.Disconnected => context.stop(self)
  }
}
