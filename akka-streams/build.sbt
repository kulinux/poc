name := "akka-streams"

version := "1.0"

scalaVersion := "2.12.2"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.2" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.2",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.2" % Test,
  "io.scalac" %% "reactive-rabbit" % "1.1.4"
)