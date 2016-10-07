package com.pako.fitman

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller







object WeightController {
  val db = new CouchbaseClient

}
class WeightController extends Controller {

  import WeightController.db


  post("/server/weight") {
    weight: Weight =>
      val weightForUser = db.get(weight.user) match {
        case Some(weights) => db.save(weight)
        case None => List(weight)
      }
      db.save(weight)
      response.created.location(s"/server/weight/${weight.user}")
  }

  get("/server/weight/:user") {
    request: Request =>
      db.get(request.getParam("user"))
  }

  get("/server/weight") {
    request: Request =>
      //db.values
  }


}
