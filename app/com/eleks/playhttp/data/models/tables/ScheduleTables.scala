package com.eleks.playhttp.data.models.tables

import com.eleks.playhttp.core.BaseTable
import com.eleks.playhttp.data.models.Schedule
import slick.jdbc.SQLServerProfile
import slick.lifted.Tag

object ScheduleTables extends {
  val profile = SQLServerProfile
} with ScheduleTables


trait ScheduleTables {
  val profile: SQLServerProfile

  import profile.api._

  class ScheduleTable(tag: Tag) extends BaseTable[Schedule](tag, None, "Schedule") {
    override val id = column[Long]("Id", O.PrimaryKey, O.AutoInc)

    def time = column[String]("Time")

    def movieId = column[Long]("MovieId")

    def price = column[Double]("Price")

    def hallId = column[Long]("HallId")

    def * = (id, time, movieId, price, hallId)<> ((Schedule.apply _).tupled, Schedule.unapply)
  }

  lazy val query = new TableQuery(tag => new ScheduleTable(tag))
}
