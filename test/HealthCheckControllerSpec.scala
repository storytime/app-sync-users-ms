import com.github.storytime.controllers.HealthCheckController
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits.global

class HealthCheckControllerSpec extends PlaySpec with Results {

  "HealthCheckController" should {

    "have valid health check" in {
      val controller = new HealthCheckController(Helpers.stubControllerComponents())
      val result = controller.getHealth().apply(FakeRequest())
      status(result) mustBe OK
    }
  }
}
