package showmyrequest

import javax.servlet._
import http.{HttpServletRequest, HttpServletResponse}
import java.io._
import java.util
import org.yaml.snakeyaml.Yaml
import collection.JavaConversions._
import com.maxmind.geoip.LookupService

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   3:26 PM
 */

object RequestViewFilter {
  lazy val locator = {
    val geoipDB = classOf[RequestViewFilter].getResource("/geoip/GeoLiteCity.dat").getPath
    new LookupService(geoipDB, LookupService.GEOIP_INDEX_CACHE)
  }
}

class RequestViewFilter extends Filter {
  def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    val out = response.getOutputStream
    val writer = new BufferedWriter(
      new OutputStreamWriter(out, "UTF-8"))

    val httpReq = request.asInstanceOf[HttpServletRequest]
    val httpResp = response.asInstanceOf[HttpServletResponse]

    val resp = new util.LinkedHashMap[String, Any]()

    resp.put("Method", httpReq.getMethod)
    resp.put("Path", httpReq.getRequestURI)
    resp.put("Query", Option.apply(httpReq.getQueryString).getOrElse(""))

    resp.put("Remote-Addr", httpReq.getRemoteAddr)
    resp.put("Remote-Port", httpReq.getRemotePort)

    val headers = new util.LinkedHashMap[String, Any]()
    httpReq.getHeaderNames.filterNot(_.toLowerCase.contains("heroku")).
      filterNot(_ == "X-Request-Start").
      foreach { key =>
        val values = httpReq.getHeaders(key).toArray
        if (values.length == 1)
          headers.put(key, values.head)
        else
          headers.put(key, values)
      }

    val forwardedKey = headers.keySet().find(_.toLowerCase == "x-forwarded-for")
    val realIP = forwardedKey match {
      case Some(v) => headers.get(v).asInstanceOf[String].split(',')(0).stripMargin
      case None => httpReq.getRemoteAddr
    }

    resp.put("Real-IP", realIP)

    try {
      val location = RequestViewFilter.locator.getLocation(realIP)
      val locMap = mutableMapAsJavaMap(collection.mutable.Map(
        "city" -> location.city,
        "country" -> location.countryName,
        "country-code" -> location.countryCode,
        "latitude" -> location.latitude,
        "longitude" -> location.longitude
      ))

      resp.put("GeoIP-Location", locMap)
    } catch { case _ => }

    resp.put("Headers", headers)
    resp.put("Parameters",  httpReq.getParameterMap)

    httpResp.setStatus(200)
    httpResp.setContentType("text/plain")

    writer.write(new Yaml().dumpAsMap(resp))
    writer.close()
    out.close()
  }

  def init(filterConfig: FilterConfig) {}

  def destroy() {}
}
