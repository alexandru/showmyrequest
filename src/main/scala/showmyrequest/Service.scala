package showmyrequest

import com.yammer.dropwizard.ScalaService
import com.yammer.dropwizard.config.Environment

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   10:48 PM
 */

object Service extends ScalaService[Configuration]("showmyrequest") {
  def initialize(conf: Configuration, env: Environment) {
    val geoip = new GeoIPLocator(conf.geoipDBPath)
    env.addFilter(new RequestViewFilter(geoip), "/")
    env.addResource(new RootResource)
    env.addHealthCheck(new GeoIPHealthCheck(conf.geoipDBPath))
  }
}
