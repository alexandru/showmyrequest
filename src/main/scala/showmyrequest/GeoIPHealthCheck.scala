package showmyrequest

import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result
import java.io.File

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   10:51 PM
 */

class GeoIPHealthCheck(dbPath: String) extends HealthCheck("geoip") {
  def check(): Result = {
    if (dbPath == null || dbPath.isEmpty)
      return Result.unhealthy("the path to GeoLiteCity.dat file has to be specified")

    val file = new File(dbPath)
    if (! file.exists())
      return Result.unhealthy("cannot find the GeoLiteCity.dat database")

    val locator = new GeoIPLocator(dbPath)
    val ret = locator.locate("174.129.9.62")

    if (ret != null && "US".equals(ret.get("country-code")))
      Result.healthy()
    else
      Result.unhealthy("not functional")
  }
}
