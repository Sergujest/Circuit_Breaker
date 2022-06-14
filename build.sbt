import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Circuit_Breaker",
    libraryDependencies ++=
      Seq(
        "com.typesafe.akka"            %% "akka-actor"                          % "2.6.1",
        scalaTest % Test,
        "com.typesafe.akka"            %% "akka-actor-typed"                    % "2.6.1",
        "com.typesafe.akka"            %% "akka-actor-testkit-typed"            % "2.6.1"   % Test,
      )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
