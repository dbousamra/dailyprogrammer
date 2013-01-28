organization := "com.github.dbousamra"

name := "dailyprogrammer"

version := "0.0.1"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq( 
  "org.scalatest" %% "scalatest" % "1.9.1",
  "net.liftweb" %% "lift-json" % "2.5-M4",
  "org.scalaj" % "scalaj-time_2.10.0-M7" % "0.6",
  "org.scalaz" %% "scalaz-core" % "7.0.0-M7",
  "com.assembla.scala-incubator" %% "graph-core" % "1.6.0"
)


// change default testing directory to main so that I can have tests and sources mixed
//sourceDirectory in Test <<= baseDirectory(_ / "src/main/")