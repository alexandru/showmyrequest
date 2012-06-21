package showmyrequest

import com.yammer.dropwizard.config.{Configuration => DWConfiguration}
import javax.validation.constraints.NotNull
import org.codehaus.jackson.annotate.JsonProperty

/**
 * Author: Alexandru Nedelcu
 * Email:  contact@alexn.org
 * Time:   10:49 PM
 */

class Configuration extends DWConfiguration {
  @NotNull
  @JsonProperty
  var geoipDBPath: String = _
}
