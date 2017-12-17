name := "jvm-utils"

organization := "markarasev"

description := "Utilitary classes for JVM-based projects."

scalaVersion := "2.12.4"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.0.0" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")

publishTo := Some("Artifactory Realm" at "http://virgo.fun:8081/artifactory/sbt-repo-local")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

enablePlugins(JavaAppPackaging)
