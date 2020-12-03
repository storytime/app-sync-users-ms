package com.github.storytime.model

import play.api.libs.json._

case class AppUser(id: Option[Long] = None,
                   zenAuthToken: String,
                   timeZone: String,
                   zenLastSyncTimestamp: Long,
                   ynabAuthToken: String = "",
                   ynabSyncEnabled: Boolean = false)

object AppUser {
  implicit val personFormat: OFormat[AppUser] = Json.format[AppUser]
}
