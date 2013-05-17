package showmyrequest

import spray.json.DefaultJsonProtocol
import shifter.geoip.GeoIPLocation
import shifter.web.api.http.UserInfo

object JsonFormat extends DefaultJsonProtocol {
  implicit val geoIPLocationJsonFormat = jsonFormat10(GeoIPLocation)
  implicit val userInfoJsonFormat = jsonFormat5(UserInfo)
}
