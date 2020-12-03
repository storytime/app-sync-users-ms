package com.github.storytime.db.component

import com.github.storytime.model.AppUser
import javax.inject.Singleton
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.JdbcProfile

@Singleton
trait AppUserComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import profile.api._

  val AppUserTable = TableQuery[AppUserTableDefinition]

  class AppUserTableDefinition(tag: Tag) extends Table[AppUser](tag, "app_user") {

    def * = (id.?,
      zenAuthToken,
      timeZone,
      zenLastSyncTimestamp,
      ynabAuthToken,
      ynabSyncEnable) <> ((AppUser.apply _).tupled, AppUser.unapply)

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def zenAuthToken = column[String]("zen_auth_token")

    def timeZone = column[String]("time_zone")

    def zenLastSyncTimestamp = column[Long]("zen_last_sync_timestamp")

    def ynabAuthToken = column[String]("ynab_auth_token")

    def ynabSyncEnable = column[Boolean]("ynab_sync_enabled", O.Default(false))
  }

}
