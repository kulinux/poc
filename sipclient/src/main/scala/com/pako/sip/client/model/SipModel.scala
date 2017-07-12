package com.pako.sip.client.model

import java.util.UUID

import com.pako.sip.client.model.SipModel.Headers.Header
import com.pako.sip.client.model.SipModel.Methods.Method
import com.pako.sip.client.model.SipModel.Request

/**
  * Created by pako on 24/6/17.
  */
object SipModel {

  case class SipAddress(user : String, host : String, port: Integer = 56987)

  case class Request(sipAddress : SipAddress,
                     destination : String,
                     method : Methods.Method,
                     headers : List[Header] = List.empty[Header]) {
    def addHeader(hd : Header) =
      Request(sipAddress, destination, method, headers :+ hd)
  }

  object Methods {
    sealed abstract class FirstLine
    sealed abstract case class Method(method : String) extends FirstLine
    object Register extends Method("REGISTER")
    case class Response(status : Int, description: String) extends FirstLine
  }

  object Headers {
    sealed abstract class Header(val header : String, val headerValue : String)

    case class Via(dst : String, port: Integer) extends Header("Via",
      s"SIP/2.0/UDP ${dst}:${port};rport;branch=${UUID.randomUUID()}")
    case class MaxForward(value : Integer = 70) extends Header("Max-Forwards", value.toString)
    case class From(sipAddress: SipAddress) extends Header("From",
      "\"" + sipAddress.user + "\" " + s"<sip:${sipAddress.user}@${sipAddress.host}>;tag=${UUID.randomUUID()}")
    case class To(sipAddress: SipAddress) extends Header("To",
      "\"" + sipAddress.host + "\" " + s"<sip:${sipAddress.user}@${sipAddress.host}>")
    case class CallId(value : String) extends Header("Call-ID", value)
    case class CSeq(value : String) extends Header("CSeq", value)
    case class UserAgent(value : String) extends Header("User-Agent", value)
    case class Contact(sipAddress: SipAddress) extends Header("Contact",
      "\"" + sipAddress.user + "\" " + s"<sip:${sipAddress.user}@${sipAddress.host}:${sipAddress.port};ob>")
    case class Expires(expires : Integer = 300) extends Header("Expires", expires.toString)
    case class ContentLength(cl : Integer = 0) extends Header("Content-Length", cl.toString)
  }

}

object SipMarshaller {
  def marshall(sipRequest: Request) =
    s"${sipRequest.method.method} sip:${sipRequest.destination} SIP/2.0" + "\n" +
      sipRequest.headers.map(x => s"${x.header}: ${x.headerValue}").mkString("\n") + "\n\n\n"

}

object SipUnMarshaller {
  def unmarshall(msg : String) = {
    val lines = msg.split("\\r?\\n")
    val met = parseMethod(lines(0))
  }

  def parseMethod(str: String) = ???
    //Method(str.split(" ")(0))

}
