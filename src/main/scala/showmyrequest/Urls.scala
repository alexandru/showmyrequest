package showmyrequest

import shifter.web.api.mvc._

object Urls extends UrlRouter {
  def route = {
    case GET("/") =>
      Controller.showRequest

    case ALL(p"/raw/set/$status{\d+}/") =>
      Controller.rawBodySet(status.toInt)

    case GET("/raw/get/last/") =>
      Controller.rawBodyGet
  }
}
