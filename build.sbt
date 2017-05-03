name := "lab-shapeless"

organization := "com.github.mboogerd"

version := "0.1"

lazy val root = project.in(file("."))
  .settings(DependenciesConf.common)
  .settings(TutConf.settings)