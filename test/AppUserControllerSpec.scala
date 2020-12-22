import com.github.storytime.controllers.AppUserController
import com.github.storytime.dao.AppUserDao
import com.github.storytime.model.AppUser
import org.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.http.Status.{NOT_FOUND, OK}
import play.api.test.Helpers.{contentAsJson, defaultAwaitTimeout, status}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AppUserControllerSpec extends PlaySpec with MockitoSugar {

  private val mockedAppUsersDao = mock[AppUserDao]
  private val USER_ID = 1L
  private val ZEN_TIME = 1608623328L

  private val usersList = Seq(
    AppUser(None, "", "", USER_ID),
    AppUser(Some(USER_ID), "", "", USER_ID),
    AppUser(None, "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", ZEN_TIME),
    AppUser(Some(USER_ID), "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", ZEN_TIME),
    AppUser(None, "cMvE7xM0fYWsbPzA", "drD4qCzMOfWbhqIT", ZEN_TIME, ""),
    AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx"),
    AppUser(None, "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true),
    AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  )
  private val notValidUsersList = Seq(AppUser(None, null, null, ZEN_TIME))
  private val validUser = AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", USER_ID, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  private val notValidUser = AppUser(None, null, null, USER_ID)

  "AppUserController" should {
    "1. getAllAppUsers can return empty list" in {
      when(mockedAppUsersDao.getAllAppUsers).thenReturn(Future(Seq.empty))

      val response = getController.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      val body = contentAsJson(response).as[Seq[AppUser]]
      body mustBe Seq.empty
      body.length mustBe Seq.empty.length
    }

    "2. getAllAppUsers can return full list" in {
      val mockedNotEmptyUsersList = Future(usersList)

      when(mockedAppUsersDao.getAllAppUsers).thenReturn(mockedNotEmptyUsersList)
      val response = getController.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      val body = contentAsJson(response).as[Seq[AppUser]]
      body.length mustBe usersList.length
      body.sortBy(_.id) mustBe usersList.sortBy(_.id)
    }

    "3. getAllAppUsers should not map empty body" in {
      when(mockedAppUsersDao.getAllAppUsers).thenReturn(Future(notValidUsersList))
      val response = getController.getAllAppUsers().apply(FakeRequest())

      status(response) mustBe OK
      intercept[Exception](contentAsJson(response).as[Seq[AppUser]])
    }

    "4. getAppUserById must return valid user" in {
      when(mockedAppUsersDao.getAppUserById(USER_ID)).thenReturn(Future(Some(validUser)))
      val response = getController.getAppUserById(USER_ID).apply(FakeRequest())

      status(response) mustBe OK
      val body = contentAsJson(response).as[AppUser]
      body mustBe validUser
    }

    "5. getAppUserById can return not found" in {
      when(mockedAppUsersDao.getAppUserById(USER_ID)).thenReturn(Future(None))
      val response = getController.getAppUserById(USER_ID).apply(FakeRequest())
      status(response) mustBe NOT_FOUND
    }

    "6. getAppUserById can return not valid body" in {
      when(mockedAppUsersDao.getAppUserById(USER_ID)).thenReturn(Future(Some(notValidUser)));
      val response = getController.getAppUserById(USER_ID).apply(FakeRequest())
      status(response) mustBe OK
      intercept[Exception](contentAsJson(response).as[AppUser])
    }
  }

  private def getController = new AppUserController(mockedAppUsersDao, Helpers.stubControllerComponents())
}