import com.github.storytime.controllers.AppUserController
import com.github.storytime.dao.AppUserDao
import com.github.storytime.model.AppUser
import org.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.http.Status.OK
import play.api.test.Helpers.{contentAsJson, defaultAwaitTimeout, status}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AppUserControllerSpec extends PlaySpec with MockitoSugar {

  private val emptyList = Future(Seq.empty)
  private val notEmptyList = Seq(
    AppUser(None, "", "", 1L),
    AppUser(Some(1L), "", "", 1L),
    AppUser(None, "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", 1L),
    AppUser(Some(1L), "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", 1L),
    AppUser(None, "cMvE7xM0fYWsbPzA", "drD4qCzMOfWbhqIT", 1L, ""),
    AppUser(Some(1L), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", 1L, "qJMLSmHEwpfaBjVx"),
    AppUser(None, "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", 1L, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true),
    AppUser(Some(1L), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", 1L, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  )

  "AppUserController" should {
    "1. getAllAppUsers can return empty list" in {
      val mockedAppUsersDao = mock[AppUserDao]

      when(mockedAppUsersDao.getAllAppUsers).thenReturn(emptyList)

      val controller = new AppUserController(mockedAppUsersDao, Helpers.stubControllerComponents())
      val response = controller.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      val body = contentAsJson(response).as[Seq[AppUser]]
      body mustBe Seq.empty
      body.length mustBe Seq.empty.length
    }

    "2. getAllAppUsers can return full list" in {
      val mockedAppUsersDao = mock[AppUserDao]
      val mockedNotEmptyUsersList = Future(notEmptyList)

      when(mockedAppUsersDao.getAllAppUsers).thenReturn(mockedNotEmptyUsersList)

      val controller = new AppUserController(mockedAppUsersDao, Helpers.stubControllerComponents())
      val response = controller.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      val body = contentAsJson(response).as[Seq[AppUser]]
      body.length mustBe notEmptyList.length
      body.toSeq.sortBy(_.id) mustBe notEmptyList.sortBy(_.id)
    }

    "3. getAllAppUsers should not map empty body" in {
      val mockedAppUsersDao = mock[AppUserDao]
      val mockedNotEmptyUsersList = Future(Seq(AppUser(None, null, null, 1L)))
      when(mockedAppUsersDao.getAllAppUsers).thenReturn(mockedNotEmptyUsersList)

      val controller = new AppUserController(mockedAppUsersDao, Helpers.stubControllerComponents())
      val response = controller.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      intercept[Exception](contentAsJson(response).as[Seq[AppUser]])
    }

  }
}