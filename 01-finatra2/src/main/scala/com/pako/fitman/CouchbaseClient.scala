package com.pako.fitman


import argonaut.Argonaut._
import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.couchbase.spark._
import com.couchbase.spark.sql._
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by fbenitez on 07/10/2016.
  */
class CouchbaseClient {


  var sc = initCouchbase()

  def initCouchbase() : SparkContext = {
    //It is important add the jvm property -Dcom.couchbase.connectTimeout=50000
    val cfg = new SparkConf()
      .setAppName("couchbaseQuickstart") // give your app a name
      .setMaster("local[*]") // set the master to local for easy experimenting
      .set("com.couchbase.bucket.finatra", "") // open the travel-sample bucket
      .set("com.couchbase.nodes", "192.168.99.100")

    sc = new SparkContext(cfg)
    sc
  }

  implicit def WeightCodecJson =
    casecodec3(Weight.apply, Weight.unapply)("user", "weight", "status")

  def save(value : Weight) = {
    val json = value.asJson.spaces2
    val doc1 = JsonDocument.create(value.user, JsonObject.fromJson(json))
    val data = sc.parallelize(Seq(doc1))
    data.saveToCouchbase()
  }

  def get(key : String) = {
    val jsonDoc = sc.couchbaseGet[JsonDocument](Seq(key)).collect()
      if(jsonDoc.length > 0) {
        val parsed: Option[Weight] =
          jsonDoc.apply(0).toString.decodeOption[Weight]
        parsed
      } else {
        None
      }
  }

  def all() = {
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val dataFrame = sqlContext.read.couchbase()
    dataFrame.show()
    val taken = dataFrame.take(20)
    println("taken")
  }
}
