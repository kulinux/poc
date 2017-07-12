name := "sipclient"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.2" % Test,
  "com.typesafe.akka" %% "akka-http" % "10.0.7",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.7" % Test
)

libraryDependencies ++= Seq("org.scalactic" %% "scalactic" % "3.0.1",
                          "org.scalatest" %% "scalatest" % "3.0.1" % "test")
