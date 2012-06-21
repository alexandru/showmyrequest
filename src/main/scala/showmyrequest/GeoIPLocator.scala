package showmyrequest

import com.maxmind.geoip.LookupService
import collection.JavaConversions._
import java.util

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   11:29 PM
 */

class GeoIPLocator(dbPath: String) {
  def locate(ip: String): util.Map[String, Any] = {
    val location = ip.split('.').length match {
      case 3 => locator.getLocation(ip)
      case _ => locator.getLocationV6(ip)
    }

    if (location == null)
      return new util.HashMap[String, Any]()

    mutableMapAsJavaMap(collection.mutable.Map(
      "city" -> location.city,
      "country" -> location.countryName,
      "country-code" -> location.countryCode,
      "latitude" -> location.latitude,
      "longitude" -> location.longitude
    ))
  }

  private[this] lazy val locator = {
    new LookupService(dbPath, LookupService.GEOIP_INDEX_CACHE)
  }
}
