package com.github.storytime.controllers

import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HealthCheckController @Inject()(cc: ControllerComponents)
                                     (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getHealth: Action[AnyContent] = Action.async(implicit request => {
    Future(Ok)
  })
}


