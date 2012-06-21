package showmyrequest

import com.yammer.metrics
import com.yammer.metrics.scala.Timer

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   12:20 AM
 */

trait Metrics {
  protected lazy val timer = {
    val metric = metrics.Metrics.defaultRegistry().newTimer(
      classOf[RequestViewFilter], "requestview")
    new Timer(metric)
  }
}
