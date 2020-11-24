lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "app-sync-users-ms",
    version := "0.1",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "org.postgresql" % "postgresql" % "42.2.18",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
      "org.mockito" %% "mockito-scala" % "1.16.3",
      specs2 % Test
    ),

    scalacOptions ++= Seq("-feature", "-deprecation", "-Xfatal-warnings")
  )
