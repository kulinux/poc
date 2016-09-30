

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

/**
  * Created by fbenitez on 28/09/2016.
  */

object FitmanApp extends FitmanServer

class FitmanServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit =  {
    router.add(new HelloController)
  }
}

class HelloController extends Controller {
  get("/hello") { request: Request =>
    "Fitman says hello"
  }
}
