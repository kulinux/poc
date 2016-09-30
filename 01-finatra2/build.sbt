name := "01-finatra2"


version := "1.0"

scalaVersion := "2.11.8"


lazy val versions = new {
  val finatra = "2.4.0"
  val logback = "1.1.3"
}


resolvers ++= Seq(
  //Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)


libraryDependencies += "com.twitter" %% "finatra-http" % versions.finatra
//libraryDependencies += "ch.qos.logback" %% "logback-classic" % versions.logback

