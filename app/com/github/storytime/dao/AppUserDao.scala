package com.github.storytime.dao

import com.github.storytime.db.query.AppUserQueries
import com.github.storytime.model.AppUser
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AppUserDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]
    with AppUserQueries {

  import profile.api._

  def getAllAppUsers: Future[Seq[AppUser]] = db.run(getAllAppUsersQuery.result)

  def getAppUserById(id: Long): Future[Option[AppUser]] = db.run(getAppUsersByIdQuery(id).result.headOption)

}
