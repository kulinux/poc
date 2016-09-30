package com.pako.fitman

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

/**
  * Created by fbenitez on 30/09/2016.
  */
class HelloControllerFeatureTest extends FeatureTest {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(
    twitterServer = new FitmanServer
  )

  "Say hello" in {
    server.httpGet (
     path = "/hello",
      andExpect = Status.Ok,
      withBody = "Hello World!"
    )
  }

  "WeightResource" should {
    "Save the weigth when post is made" in {
      server.httpPost(
        path = "/weigths",
        postBody =
          """
            |{
            |"user" : "pako",
            | "weigth": 90,
            | "status": "Need more beer"
            |}
          """.stripMargin,
        andExpect = Status.Created,
        withLocation = "/weight/pako"
      )
    }
  }
}
