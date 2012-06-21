package showmyrequest

import javax.ws.rs._
import javax.ws.rs.core.{Context, MediaType}
import javax.servlet.http.HttpServletRequest
import collection.mutable
import org.yaml.snakeyaml.Yaml
import java.util

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   10:56 PM
 */

@Path("/")
@Produces(Array(MediaType.TEXT_PLAIN))
class RootResource {
  @GET
  def root = "ShowMyRequest"
}
