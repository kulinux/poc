package com.pako.Pi


import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinRouter




/**
  * Created by fbenitez on 11/11/2016.
  */

sealed trait PiMessage

case object Calculate extends PiMessage {
  def calculatePi(start : Int, noElements : Int): Double = {
    var acc = 0.0
    for (i ← start until (start + noElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }
}
case class Work(start : Int, noElements : Int) extends PiMessage
case class Result(value : Double) extends PiMessage
case class PiApproximation(pi: Double, duration : Long) extends PiMessage


class Worker extends Actor {
  override protected def receive: Receive = {
    case Work(start, noElements) => {
      sender ! Result(Calculate.calculatePi(start, noElements))
    }
  }
}

class Master(noWorkers : Int,
             noMsg : Int,
             noElements : Int,
             listener : ActorRef)
  extends Actor {

  val workerRouter = context.actorOf(
   Props[Worker].withRouter(RoundRobinRouter(noWorkers)), name = "workersRoute"
  )


  var pi : Double = _
  var noResult : Int = _
  val start = System.currentTimeMillis

  override protected def receive: Receive = {
    case Calculate =>
      for( i : Int <- noMsg) workerRouter ! Work(i * noElements, noElements)
    case Result (value : Double) =>
      pi += value
      noResult += 1
      if (noResult == noMsg) {
        listener ! PiApproximation(pi, System.currentTimeMillis - start)
        context.stop(self)
      }


  }
}

class Listener extends Actor {
  override protected def receive: Receive = {
    case PiApproximation(pi : Double, duration : Long)  =>
      println("Aproximacion pi %s time %s".format(pi, duration))
  }
}


object Pi extends App {
  def calculate(noWorker : Int, noElements : Int, noMsg : Int): Unit = {
    val system = ActorSystem("PiSystem")

    val listener = system.actorOf(Props[Listener], name = "listener")

    val master = system.actorOf(Props(new Master(noWorker, noMsg, noElements, listener)),
      name = "master")

    master ! Calculate
  }
}
