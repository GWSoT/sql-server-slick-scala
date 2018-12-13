name := "PlayHttp"

version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(guice)
libraryDependencies += "com.typesafe.play" %% "play" % "2.6.20"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.3"
libraryDependencies +=  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3"
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
)
libraryDependencies += "com.microsoft.sqlserver" % "mssql-jdbc" % "7.0.0.jre10"


