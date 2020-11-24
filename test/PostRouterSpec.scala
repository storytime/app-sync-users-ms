//import com.github.storytime.model.AppUser
//import org.scalatestplus.play._
//import org.scalatestplus.play.guice._
//import play.api.libs.json.Json
//import play.api.test.CSRFTokenHelper._
//import play.api.test.Helpers._
//import play.api.test._
//
//class PostRouterSpec extends PlaySpec with GuiceOneAppPerTest {
//
//  "PostRouter" should {
//
//    "render the list of posts" in {
//      val request = FakeRequest(GET, "/users").withCSRFToken
//      val home = route(app, request).get
//
//      val posts: Seq[AppUser] AppUser = Json.fromJson[Seq[AppUser]](contentAsJson(home)).get
//      println(posts.size)
//    }
//  }
//
//}