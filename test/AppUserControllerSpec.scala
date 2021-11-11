import com.github.storytime.controllers.AppUserController
import com.github.storytime.dao.AppUserDao
import com.github.storytime.model.AppUser
import org.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.http.Status.{INTERNAL_SERVER_ERROR, NOT_FOUND, OK}
import play.api.libs.json.Json
import play.api.test.Helpers.{contentAsJson, defaultAwaitTimeout, status}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AppUserControllerSpec extends PlaySpec with MockitoSugar {

  private val mockedAppUsersDao = mock[AppUserDao]
  private val USER_ID = 1L
  private val UPDATED_COUNT = 1
  private val UPDATED_FAILED_COUNT = -1
  private val ZEN_TIME = 1608623328L

  private val EMPTY_STR = ""
  private val usersList = Seq(
    AppUser(None, EMPTY_STR, EMPTY_STR, USER_ID),
    AppUser(Some(USER_ID), EMPTY_STR, EMPTY_STR, USER_ID),
    AppUser(None, "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", ZEN_TIME),
    AppUser(Some(USER_ID), "qeFHFmo5yEU8NJCY", "ibXBWtDZoSEaSAJ1", ZEN_TIME),
    AppUser(None, "cMvE7xM0fYWsbPzA", "drD4qCzMOfWbhqIT", ZEN_TIME, EMPTY_STR),
    AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx"),
    AppUser(None, "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true),
    AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  )
  private val notValidUsersList = Seq(AppUser(None, null, null, ZEN_TIME))
  private val validUser = AppUser(Some(USER_ID), "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  private val validUserNoId = AppUser(None, "7UoeYmUgGfK6jx02", "x75OjmQnpCbqrMGo", ZEN_TIME, "qJMLSmHEwpfaBjVx", ynabSyncEnabled = true)
  private val notValidUser = AppUser(None, null, null, ZEN_TIME)

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

    "7. update can save user valid" in {
      when(mockedAppUsersDao.update(validUser)).thenReturn(Future(UPDATED_COUNT));
      val response = getController.updateUser().apply(FakeRequest().withJsonBody(Json.toJson(validUser)))
      status(response) mustBe OK
    }

    "8. update cannot save user with not empty ID" in {
      when(mockedAppUsersDao.update(validUserNoId)).thenReturn(Future(UPDATED_FAILED_COUNT));
      val response = getController.updateUser().apply(FakeRequest().withJsonBody(Json.toJson(validUserNoId)))
      status(response) mustBe INTERNAL_SERVER_ERROR
    }

    "9. update cannot save not valid user" in {
      when(mockedAppUsersDao.update(notValidUser)).thenReturn(Future(UPDATED_FAILED_COUNT));
      val response = getController.updateUser().apply(FakeRequest().withJsonBody(Json.toJson(notValidUser)))
      status(response) mustBe INTERNAL_SERVER_ERROR
    }

    "10. update accept not valid body" in {
      intercept[Exception](getController.updateUser().apply(FakeRequest().withJsonBody(Json.toJson(EMPTY_STR))))
    }
  }

  private def getController = new AppUserController(mockedAppUsersDao, Helpers.stubControllerComponents())
}