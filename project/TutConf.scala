import sbt.Keys._
import tut.Plugin._

object TutConf {

  lazy val settings = tutSettings ++ Seq(
    // Location of tut source files.
//    tutSourceDirectory := sourceDirectory.value / "main" / "tut",

    // Regex specifying files that should be interpreted.
//     tutNameFilter := r".*" // default: Names ending in .md .txt .htm .html

    // Destination for tut output.
    tutTargetDirectory := (baseDirectory / "doc").value // default: crossTarget.value / "tut"

    // Compiler options that will be passed to the tut REPL.
//      tutScalacOptions := // default: Same as Test configuration.

    // List of compiler plugin jarfiles to be passed to the tut REPL.
//      tutPluginJars := // Same as Test configuration.
  )
}