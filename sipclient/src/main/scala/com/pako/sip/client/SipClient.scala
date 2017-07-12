package com.pako.sip.client

import java.net.InetSocketAddress

import akka.actor.{ActorSystem, Props}
import com.pako.sip.client.connection.{SipMessage, UdpActor}

/**
  * Created by pako on 14/6/17.
  */



object SipClient extends App {
  val remote = new InetSocketAddress("localhost", 5060)
  val system =
    ActorSystem.create("test")
  val actor= system.actorOf(Props(new UdpActor( remote )))
  Thread.sleep(5000)
  actor ! SipMessage.Msg
  println("Message sent")
  Thread.sleep(1000)
  //actor ! PoisonPill
  system.terminate()
}
