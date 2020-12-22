import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class AppIntegrationSpec extends Specification {

  "Application" should {

    "1. send req to wrong resource" in new WithApplication {
      route(app, FakeRequest(GET, "/blablabla")) must beSome.which(status(_) == INTERNAL_SERVER_ERROR)
    }
  }

}
