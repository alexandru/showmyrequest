package showmyrequest

import shifter.web.jetty9.{FilterConfig, Context, LifeCycle}
import shifter.web.api.ShifterFilter

class ServerLifeCycle extends LifeCycle {
  def createContext: Context =
    Context(filters = List(
      FilterConfig("showmyrequest", ShifterFilter(Urls), "/*")
    ))

  def destroyContext(ctx: Context) {}
}