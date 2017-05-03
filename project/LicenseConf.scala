import de.heikoseeberger.sbtheader.HeaderKey._
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt.Keys._
import sbt._

object LicenseConf {

  lazy val settings = Seq(
    licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php")),
    headers := Map(
      "scala" -> Apache2_0("2015", "Merlijn Boogerd"),
      "conf" -> Apache2_0("2015", "Merlijn Boogerd", "#")
    )
  )
}