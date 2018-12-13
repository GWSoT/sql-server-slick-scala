package com.eleks.playhttp.data.models

import com.eleks.playhttp.data.models.tables.core.BaseEntity
import play.api.libs.json.Json


case class User(id: Long = 0, name: String, country: String) extends BaseEntity

object User {
  implicit val userFormat = Json.format[User]
}