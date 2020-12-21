import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "1. send 404 on a bad request" in new WithApplication {
      route(app, FakeRequest(GET, "/blablabla")) must beSome.which(status(_) == NOT_FOUND)
    }
  }

}
