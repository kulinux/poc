package com.pako.sip.client.connection

import java.net.InetSocketAddress

import akka.actor.{ActorSystem, Props}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by pako on 25/6/17.
  */
class UdpActorIt extends FlatSpec with Matchers {

  "Sip Message" should "be sent in plain test" in {
    val remote = new InetSocketAddress("localhost", 5060)
    val system =
      ActorSystem.create("test")
    val actor= system.actorOf(Props(new UdpActor( remote )))
    Thread.sleep(5000)
    println(SipMessage.Msg)
    actor ! SipMessage.Msg
    println("Message sent")
    Thread.sleep(1000)
    //actor ! PoisonPill
    system.terminate()
  }



}
