package com.pako.fitman

import java.time.Instant

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import scala.collection.mutable

/**
  * Created by fbenitez on 28/09/2016.
  */

object FitmanApp extends FitmanServer

class FitmanServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit =  {
    router.add(new HelloController)
    router.add(new WeightController)
  }
}



class HelloController extends Controller {
  get("/hello") { request: Request =>
    "Hello World!"
  }
}

case class Weigth (
                    user : String,
                    weigth : Int,
                    status: Option[String],
                    postedAt : Instant = Instant.now
                  )

class WeightController extends Controller {

  val db = mutable.Map[String, List[Weigth]]()

  post("/weigths") {
    weight: Weigth =>
      val weigthForUser = db.get(weight.user) match {
        case Some(weigths) => weigths :+ weight
        case None => List(weight)
      }
      db.put(weight.user, weigthForUser)
      response.created.location(s"/weight/${weight.user}")
  }


}
