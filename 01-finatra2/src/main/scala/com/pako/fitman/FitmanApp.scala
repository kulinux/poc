package com.pako.fitman

import com.twitter.finagle.http.Request
import com.twitter.finagle.http.filter.Cors
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

/**
  * Created by fbenitez on 28/09/2016.
  */

object FitmanApp extends FitmanServer

class FitmanServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit =  {
    router
      .filter(new Cors.HttpFilter(Cors.UnsafePermissivePolicy))
      .add(new HelloController)
      .add(new WeightController)
      .add(new ResourcesController)
  }
}

class ResourcesController extends Controller {
  get("/assets/:*") { request: Request =>
    response.ok.file("/public/" + request.params("*"))
  }
}



class HelloController extends Controller {
  get("/hello") { request: Request =>
    "Hello World!"
  }
}

