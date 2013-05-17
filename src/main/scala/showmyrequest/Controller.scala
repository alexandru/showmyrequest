package showmyrequest

import shifter.web.api.mvc._
import shifter.web.api.mvc.{Controller => AppController}
import spray.json._
import JsonFormat._
import shifter.web.api.responses.SimpleResult
import java.io.{FileInputStream, FileOutputStream, File}
import scala.util.Try


object Controller extends AppController {

  def showRequest = Action { request =>
    Ok(JsObject(
      "user_info" -> request.userInfo.toJson,
      "headers" -> request.headers.toJson
    ).prettyPrint, JSON)
  }

  def rawBodySet(returnCode: Int = 200) = Action(parse.raw) { request =>
    this.synchronized {
      val in = request.body
      val outFile = File.createTempFile("raw", "request")
      outFile.deleteOnExit()
      val out = new FileOutputStream(outFile)

      try {
        val arr = new Array[Byte](4000)
        var bytesRead = 0

        while (bytesRead > -1) {
          bytesRead = in.read(arr)
          if (bytesRead > 0)
            out.write(arr, 0, bytesRead)
        }

        out.close()
        outFile.renameTo(new File("/tmp/rawRequest"))
      }
      finally {
        Try(out.close())
      }

      SimpleResult(returnCode)
    }
  }

  def rawBodyGet = Action {
    val file = new File("/tmp/rawRequest")

    if (!file.exists())
      NotFound("404 Not Found")

    else
      OkStream(new FileInputStream(file), Map(
        CONTENT_DISPOSITION -> "attachment; filename=rawRequest",
        CONTENT_TYPE -> BINARY
      ))
  }
}
