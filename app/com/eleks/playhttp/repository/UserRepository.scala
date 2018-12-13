package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.User
import com.eleks.playhttp.data.models.tables.UserTables.UserTable
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext


@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends BaseRepository[UserTable, User](TableQuery[UserTable], dbConfigProvider) {
  val query = TableQuery[UserTable]
}
