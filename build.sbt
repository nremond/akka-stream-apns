import Dependencies._
import sbt.Keys._
import scalariform.formatter.preferences._

lazy val commonSettings = scalariformSettings ++ Seq(
  organization := "com.reactivehub",
  version := "0.2-SNAPSHOT",
  scalaVersion := "2.11.7",

  licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))),
  scmInfo := Some(ScmInfo(url("https://github.com/reactive-hub/akka-stream-apns"),
    "git@github.com:reactive-hub/akka-stream-apns.git")),

  bintrayOrganization := Some("reactivehub"),
  publishMavenStyle := true,
  pomIncludeRepository := (_ ⇒ false),
  pomExtra := (
    <developers>
      <developer>
        <id>marcelmojzis</id>
        <name>Marcel Mojzis</name>
      </developer>
    </developers>
  ),

  ScalariformKeys.preferences := FormattingPreferences()
    .setPreference(RewriteArrowSymbols, true)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
)

lazy val root = (project in file("."))
  .aggregate(connector, examples)
  .settings(commonSettings)
  .settings(
    name := "akka-stream-apns-root",
    publishArtifact := false,
    publish := (),
    publishLocal := ()
  )

lazy val connector = (project in file("connector"))
  .settings(commonSettings)
  .settings(
    name := "akka-stream-apns",
    libraryDependencies ++= connectorDeps
  )

lazy val examples = (project in file("examples"))
  .dependsOn(connector)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= examplesDeps,
    publishArtifact := false,
    publish := (),
    publishLocal := ()
  )
