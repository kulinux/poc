package stream

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.Future

/**
  * Created by pako on 26/5/17.
  */
object AkkaStreamSimple2 extends App {

  implicit val system = ActorSystem("ActorSytem")
  implicit val materializer = ActorMaterializer()

  val source : Source[Int, NotUsed] = Source(1 to 100)

  val factorial = source.scan(BigInt(1))((acc, next) => acc * next)

  /*
  val done = factorial
            .map(num => ByteString(s"$num\n"))
            .runWith(FileIO.toPath(Paths.get("factorial.txt")))
            */

  val done = factorial.map(_.toString).runWith(lineShink("factorial.txt"))

  implicit val ec = system.dispatcher

  done.onComplete( _ => system.terminate() )


  def lineShink(filename : String) : Sink[String, Future[IOResult]] =
    Flow[String]
    .map(num => ByteString(s"$num\n"))
    .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)


}