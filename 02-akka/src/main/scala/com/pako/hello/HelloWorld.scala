package com.pako.hello

import akka.actor.{Actor, Props}

/**
  * Created by fbenitez on 19/11/2016.
  */

object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  override def receive: Receive = {
    case Greeter.Greet =>
      println("Greeter Hello World!")
      sender ! Greeter.Done
  }
}

class HelloWorld extends Actor {
  override def preStart(): Unit = {
    val greeters = context.actorOf(Props[Greeter], "greeter")
    greeters ! Greeter.Greet
  }

  override def receive: Receive = {
    case Greeter.Done =>
      println("Greeter Done!")
      context.stop(self)
  }
}


object Main extends App {
  println("Main::start")
 akka.Main.main(Array(classOf[HelloWorld].getName))
}
