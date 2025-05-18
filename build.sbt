import sbtassembly.AssemblyPlugin.autoImport._
import sbtassembly.MergeStrategy

name := "TrafficWeatherAnalytics"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "3.4.1",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.4.1",
  "com.typesafe" % "config" % "1.4.2",
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.discard
  case PathList("META-INF", "versions", _ @ _*)             => MergeStrategy.discard
  case PathList("META-INF", "org", _ @ _*)                   => MergeStrategy.discard
  case PathList("META-INF", "services", _ @ _*)              => MergeStrategy.filterDistinctLines
  case PathList("META-INF", xs @ _*) if xs.nonEmpty && xs.last.toLowerCase.endsWith(".sf") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) if xs.nonEmpty && xs.last.toLowerCase.endsWith(".dsa") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) if xs.nonEmpty && xs.last.toLowerCase.endsWith(".rsa") => MergeStrategy.discard
  case "reference.conf"                                     => MergeStrategy.concat
  case "application.conf"                                   => MergeStrategy.concat
  case "module-info.class"                                  => MergeStrategy.discard
  case _                                                    => MergeStrategy.first
}