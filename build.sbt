version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.3"

libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test"
libraryDependencies += "org.mockito" % "mockito-scala_2.13" % "1.6.2" % "test"
libraryDependencies += "de.saxsys" % "mvvmfx-easydi" % "1.8.0"

scalacOptions ++= Seq("-deprecation", "-feature")

