package rabbitmq

import java.time.Duration

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import io.scalac.amqp.{Address, Connection, ConnectionSettings}

/**
  * Created by pako on 29/5/17.
  */
object AkkaRabbit extends App {


  val conn = Connection()


  val source = conn.consume(queue = "pako_source")

  val dest = conn.publish(exchange = "pako_destination", routingKey = "source")

  implicit val system = ActorSystem.create("system")
  implicit val materializer = ActorMaterializer()

  Source.fromPublisher(source).map(
    x => {
      println(x.message)
      x.message
    })
    .runWith(Sink.fromSubscriber(dest))



}
