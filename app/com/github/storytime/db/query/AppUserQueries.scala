package com.github.storytime.db.query

import com.github.storytime.db.component.AppUserComponent
import com.github.storytime.model.AppUser
import javax.inject.Singleton
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.JdbcProfile

@Singleton
trait AppUserQueries extends AppUserComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import profile.api._

  def getAllAppUsersQuery: Query[AppUserTableDefinition, AppUser, Seq] = AppUserTable

  def getAppUsersByIdQuery(id: Long): Query[AppUserTableDefinition, AppUser, Seq] = AppUserTable.filter(_.id === id)

}
