package stream

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import scala.concurrent.duration._

import scala.concurrent.Future

/**
  * Created by pako on 26/5/17.
  */
object AkkaStreamSimple3 extends App {

  implicit val system = ActorSystem("ActorSytem")
  implicit val materializer = ActorMaterializer()

  val source : Source[Int, NotUsed] = Source(1 to 100)

  val factorial = source.scan(BigInt(1))((acc, next) => acc * next)

  val done = factorial.zipWith(source)((x, y) => s"$y! = $x")
    .throttle(2, 0.5.seconds, 5, ThrottleMode.Shaping)
    .runForeach(println)


  implicit val ec = system.dispatcher

  done.onComplete( _ => system.terminate() )

}