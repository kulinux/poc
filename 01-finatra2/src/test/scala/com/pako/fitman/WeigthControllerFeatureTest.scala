package com.pako.fitman

import com.pako.fitman
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

/**
  * Created by fbenitez on 30/09/2016.
  */
class WeigthControllerFeatureTest extends FeatureTest {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(
    twitterServer = new FitmanServer
  )

  "List All Weigths" in {
    val json =
      """
        |{
        |"user" : "pako",
        | "weight": 90,
        | "status": "Need more beer"
        |}
      """.stripMargin

      val response = server.httpPost(
        path = "/server/weight",
        postBody = json,
        andExpect = Status.Created
      )
      val responseGet = server.httpGetJson[List[Weight]] (
        path = response.location.get,
        andExpect = Status.Ok
      )

    responseGet should have length 1
    responseGet(0) should matchPattern { case fitman.Weight("pako", _, _) => }

  }
}
