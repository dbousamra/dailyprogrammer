organization := "com.github.dbousamra"

name := "dailyprogrammer"

version := "0.0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq( 
  "org.scalatest" %% "scalatest" % "1.9.1",
  "net.liftweb" %% "lift-json" % "2.5-M4",
  "org.scalaj" %% "scalaj-time" % "0.6"
)


// change default testing directory to main so that I can have tests and sources mixed
sourceDirectory in Test <<= baseDirectory(_ / "src/main/")