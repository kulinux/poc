package com.pako.fitman

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by fbenitez on 11/10/2016.
  */
class CouchbaseClientTest extends FlatSpec with Matchers {

  //Remeber do it this in couchbase CREATE PRIMARY INDEX `#primary` ON `finatra`
  "Couchbase" should "work" in {
    var client = new CouchbaseClient()
    val res = client.all()
    println("End "  + res)
  }

}
