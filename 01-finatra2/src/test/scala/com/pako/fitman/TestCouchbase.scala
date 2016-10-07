package com.pako.fitman

import com.couchbase.client.java.document.json.{JsonArray, JsonObject}
import com.couchbase.client.java.document.{JsonArrayDocument, JsonDocument}
import com.couchbase.spark._
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{FlatSpec, Matchers}
import argonaut._, Argonaut._

/**
  * Created by fbenitez on 07/10/2016.
  */
class TestCouchbase extends FlatSpec with Matchers {

  "Object " should "Serialize" in {
    val weight = new Weight("uno", 33, Some("tres"))
    implicit def WeightCodecJson =
      casecodec3(Weight.apply, Weight.unapply)("user", "weight", "status")

    println( weight.asJson.spaces2 )
  }
  "Couchbase " should "Connect to couchbase" in {
    val cfg = new SparkConf()
      .setAppName("couchbaseQuickstart") // give your app a name
      .setMaster("local[*]") // set the master to local for easy experimenting
      .set("com.couchbase.bucket.finatra", "") // open the travel-sample bucket
      .set("com.couchbase.nodes", "192.168.99.100")


    val sc = new SparkContext(cfg)

    val doc1 = JsonDocument.create("doc1",
      JsonObject.create().put("some","content"))
    val doc2 = JsonArrayDocument.create("doc2", JsonArray.from("more", "content", "in", "here"))

    val data = sc.parallelize(Seq(doc1, doc2))

    data.saveToCouchbase()

  }

}
