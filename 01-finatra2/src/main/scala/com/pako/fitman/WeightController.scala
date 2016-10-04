package com.pako.fitman

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import scala.collection.mutable


case class Weigth (
                    user : String,
                    weight : Int,
                    status: Option[String]
                  )


object WeightController {
  val db = mutable.Map[String, List[Weigth]]()
}
class WeightController extends Controller {

  import WeightController.db

  post("/server/weight") {
    weight: Weigth =>
      val weightForUser = db.get(weight.user) match {
        case Some(weights) => weights :+ weight
        case None => List(weight)
      }
      db.put(weight.user, weightForUser)
      response.created.location(s"/server/weight/${weight.user}")
  }

  get("/server/weight/:user") {
    request: Request =>
      db.getOrElse(request.getParam("user"), List())
  }

  get("/server/weight") {
    request: Request =>
      db.values
  }


}