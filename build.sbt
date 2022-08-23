name := "tasmota-config-manager"
version := "0.1.0-SNAPSHOT"
val scala3Version = "3.1.3"
val zioVersion = "2.0.0"
val org = "net.robmwalsh"

developers := List(
      Developer(
        "robmwalsh",
        "Rob Walsh",
        "rob@robmwalsh.net",
        url("https://github.com/robmwalsh/")
      )
    )

val sharedSettings =
  Seq(
    fork := true,//remove once zio version is bumped
    scalaVersion := scala3Version,
    organization := org,
    scalacOptions ++= Seq("-no-indent", "-rewrite"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion % "test",
      "dev.zio" %% "zio-test-sbt" % zioVersion % "test",
      "dev.zio" %% "zio-process" % zioVersion
    )
  )

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val root = project
  .in(file("."))
  .aggregate(mqtt, configManager)
  .settings(
    sharedSettings
  )

lazy val mqtt = project
  .in(file("modules/zio-mqtt"))
  .settings(
    sharedSettings,
    name := "zio-mqtt",
    libraryDependencies ++= Seq(
      "org.eclipse.paho" % "org.eclipse.paho.mqttv5.client" % "1.2.5"
    )
  )

lazy val configManager = project
  .in(file("modules/config-manager"))
  .settings(
    sharedSettings,
    name := "tasmota-config-manager",
    libraryDependencies ++= Seq(
      "io.d11" %% "zhttp" % "2.0.0-RC10"
    )
  )