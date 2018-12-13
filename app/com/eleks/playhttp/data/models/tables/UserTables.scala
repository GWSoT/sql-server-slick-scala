package com.eleks.playhttp.data.models.tables

import com.eleks.playhttp.data.models.User
import com.eleks.playhttp.data.models.tables.core.BaseTable
import slick.jdbc.SQLServerProfile
import slick.jdbc.SQLServerProfile.api._

object UserTables extends {
  val profile = SQLServerProfile
} with UserTables

trait UserTables {
  val profile: SQLServerProfile

  import profile.api._

  class UserTable(tag: Tag) extends BaseTable[User](tag, Some("application"), "users") {
    override val id = column[Long]("UserId", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def country = column[String]("country")

    def * = (id, name, country) <> ((User.apply _).tupled, User.unapply)
  }

  lazy val query = new TableQuery(tag => new UserTable(tag))
}
