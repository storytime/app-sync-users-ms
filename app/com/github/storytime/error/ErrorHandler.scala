package com.github.storytime.error

import com.github.storytime.utils.ClassLazyLogger
import play.api._
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router

import javax.inject._
import scala.concurrent._

@Singleton
class ErrorHandler @Inject()(env: Environment, c: Configuration, sm: OptionalSourceMapper, r: Provider[Router])
  extends DefaultHttpErrorHandler(env, c, sm, r)
    with ClassLazyLogger {

  val EMPTY = ""

  override def onProdServerError(r: RequestHeader, e: UsefulException): Future[Result] = handleError(r, e)

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    LOGGER.error(s"An client error occurs for request: [${request.uri}], code: [$statusCode], message: [$message]")
    Future.successful(InternalServerError(EMPTY))
  }

  override protected def onDevServerError(r: RequestHeader, e: UsefulException): Future[Result] = handleError(r, e)

  private def handleError(r: RequestHeader, e: UsefulException): Future[Result] = {
    implicit val request: RequestHeader = r
    LOGGER.error(s"An server error occurs for request: [${request.uri}], error: [${e.getMessage}]", e)
    Future.successful(InternalServerError(EMPTY))
  }
}
