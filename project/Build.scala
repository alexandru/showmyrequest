import sbt._
import sbt.{Build => SbtBuild}
import sbt.Keys._
import com.typesafe.sbt.SbtStartScript


object Build extends SbtBuild {
  lazy val godzilla = Project(
    id = "showmyrequest",
    base = file("."),
    settings = Project.defaultSettings ++ SbtStartScript.startScriptForClassesSettings ++ Seq(
      name := "showmyrequest",
      organization := "epigrams",
      version := "2.0",

      scalaVersion in ThisBuild := "2.10.1",

      scalacOptions in ThisBuild ++= Seq(
        "-unchecked", "-deprecation", "-feature"
      ),

      resolvers ++= Seq(
        "BionicSpirit Releases" at "http://maven.bionicspirit.com/releases/",
        "BionicSpirit Snapshots" at "http://maven.bionicspirit.com/snapshots/",
        "Spray Releases" at "http://repo.spray.io",
        "Spy" at "http://files.couchbase.com/maven2/",
        "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"
      ),
      libraryDependencies ++= Seq(
        "shifter" %% "shifter-geoip" % "0.3.109-SNAPSHOT",
        "shifter" %% "shifter-web-api" % "0.3.109-SNAPSHOT",
        "shifter" %% "shifter-web-jetty9" % "0.3.109-SNAPSHOT"
      ),

      mainClass in Compile := Some("showmyrequest.Server")
    )
  )
}
