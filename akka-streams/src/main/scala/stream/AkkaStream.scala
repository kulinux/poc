package stream


import akka.{ NotUsed, Done }
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

/**
  * Created by pako on 26/5/17.
  */
object AkkaStreamSimple extends App {

  implicit val system = ActorSystem("ActorSytem")
  implicit val materializer = ActorMaterializer()
  val source : Source[Int, NotUsed] = Source(1 to 100)

  val done = source.runForeach(i => println(i))(materializer)

  implicit val ec = system.dispatcher

  done.onComplete( _ => system.terminate() )

}