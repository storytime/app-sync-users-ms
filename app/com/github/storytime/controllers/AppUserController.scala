package com.github.storytime.controllers

import com.github.storytime.dao.AppUserDao
import com.github.storytime.utils.ClassLazyLogger
import com.google.inject.internal.util.Stopwatch
import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class AppUserController @Inject()(appUserDao: AppUserDao,
                                  cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc)
    with ClassLazyLogger {

  def getAllAppUsers: Action[AnyContent] = Action.async { implicit request =>
    val sw = new Stopwatch()
    LOGGER.debug(s"Starting getting all app users...")

    appUserDao.getAllAppUsers.map(users => {
      LOGGER.debug(s"Finish getting all app users - success, size: [${users.length}], time: [${sw.reset()}] ms")
      Ok(Json.toJson(users.sortBy(_.id))).as(MimeTypes.JSON)
    })
  }

  def getAppUserById(userId: Long): Action[AnyContent] = Action.async { implicit request =>
    val sw = new Stopwatch()
    LOGGER.debug(s"Starting getting user by id: [$userId] ...")

    appUserDao.getAppUserById(userId).map {
      case Some(u) =>
        LOGGER.debug(s"Finish getting user by id: [$userId] - success, time: [${sw.reset()}] ms")
        Ok(Json.toJson(u)).as(MimeTypes.JSON)
      case _ =>
        LOGGER.debug(s"Finish getting user by id: [$userId] - not found time: [${sw.reset()}] ms")
        NotFound.as(MimeTypes.JSON)
    }
  }
}

