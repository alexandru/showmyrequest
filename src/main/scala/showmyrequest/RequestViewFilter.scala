package showmyrequest

import javax.servlet._
import http.{HttpServletRequest, HttpServletResponse}
import java.io._
import java.util
import org.yaml.snakeyaml.Yaml
import collection.JavaConversions._

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   3:26 PM
 */

class RequestViewFilter(locator: GeoIPLocator) extends Filter with Metrics {
  def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    timer.time {
      val out = response.getOutputStream
      val writer = new BufferedWriter(
        new OutputStreamWriter(out, "UTF-8"))

      val httpReq = request.asInstanceOf[HttpServletRequest]
      val httpResp = response.asInstanceOf[HttpServletResponse]

      val resp = new util.LinkedHashMap[String, Any]()

      resp.put("Method", httpReq.getMethod)
      resp.put("Remote-Addr", httpReq.getRemoteAddr)
      resp.put("Remote-Port", httpReq.getRemotePort)

      val headers = new util.LinkedHashMap[String, Any]()
      httpReq.getHeaderNames.filterNot(_.toLowerCase.contains("heroku")).
        filterNot(_ == "X-Request-Start").
        foreach {
        key =>
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
      resp.put("GeoIP-Location", locator.locate(realIP))

      resp.put("Headers", headers)
      resp.put("Parameters", httpReq.getParameterMap)

      httpResp.setStatus(200)
      httpResp.setContentType("text/plain")

      writer.write(new Yaml().dumpAsMap(resp))
      writer.close()
      out.close()
    }
  }

  def init(filterConfig: FilterConfig) {}

  def destroy() {}
}
