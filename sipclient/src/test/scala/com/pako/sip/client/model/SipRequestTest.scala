package com.pako.sip.client.model

import java.util.UUID

import com.pako.sip.client.model.SipModel.{Headers, Methods, Request, SipAddress}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by pako on 21/6/17.
  */

object SipRequestTest {
  def buildRequest = {
    val sipAddress = SipAddress("uno11", "localhost")

    val method = Methods.Register

    Request(
      sipAddress,
      "localhost",
      Methods.Register)
      .addHeader(Headers.Via("192.168.1.133", 56987))
      .addHeader(Headers.MaxForward())
      .addHeader(Headers.From(sipAddress))
      .addHeader(Headers.To(sipAddress))
      .addHeader(Headers.CallId(UUID.randomUUID().toString))
      .addHeader(Headers.CSeq(s"5854 ${method.method}"))
      .addHeader(Headers.UserAgent("Telephone 1.2.6"))
      .addHeader(Headers.Contact(sipAddress))
      .addHeader(Headers.Expires())
      .addHeader(Headers.ContentLength())
  }
}
class SipRequestTest extends FlatSpec with Matchers {
  "A Sip message" should "be build" in {

    var request = SipRequestTest.buildRequest

    val res = SipMarshaller.marshall(request)

    println(res)

    assert(res.size > 0)
  }

  "A Sip message" should "be parsed" in {
    SipUnMarshaller.unmarshall()
  }
}
