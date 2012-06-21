package displaymyrequest

import org.eclipse.jetty.server.{Server => JettyServer}
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.eclipse.jetty.server.nio.SelectChannelConnector

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   3:17 PM
 */

object Server extends App {
  val server = new JettyServer()

  val host = "0.0.0.0"
  val port = if (System.getProperty("PORT") != null)
    System.getProperty("PORT")
  else
    "8080"

  val connector = new SelectChannelConnector()
  connector.setPort(Integer.valueOf(port))
  connector.setHost(host)
  server.addConnector(connector)

  val pool = new QueuedThreadPool()
  pool.setMinThreads(10)
  pool.setMaxThreads(50)
  server.setThreadPool(pool)

  val webXmlPath = this.getClass.getResource("/WEB-INF/web.xml").toExternalForm
  val resourceBase = this.getClass.getResource("/assets/").toExternalForm

  val context = new WebAppContext()
  context.setDescriptor(webXmlPath)
  context.setResourceBase(resourceBase)
  context.setContextPath("/")
  context.setParentLoaderPriority(true)

  server.setHandler(context)
  server.start()
  server.join()
}
