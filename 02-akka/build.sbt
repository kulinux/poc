import NativePackagerHelper._

name := "Akka"

version := "1.0"

scalaVersion := "2.11.7"


resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.4-SNAPSHOT"

//error in intellij, sbt version
enablePlugins(JavaAppPackaging)


mainClass in (Compile) := Some("com.pako.pi.Pi")


//sbt docker:publishLocal -> builds the sbt docker image
//docker run akka:1.0 -> run image


