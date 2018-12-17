package com.eleks.playhttp.data.models.tables

import com.eleks.playhttp.core.BaseTable
import com.eleks.playhttp.data.models.Movie
import slick.jdbc.SQLServerProfile
import slick.jdbc.SQLServerProfile.api._

object MovieTables extends {
  val profile = SQLServerProfile
} with MovieTables

trait MovieTables {
  val profile: SQLServerProfile

  import profile.api._

  class MovieTable(tag: Tag) extends BaseTable[Movie](tag, None, "Movie") {
    override val id = column[Long]("Id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("Name")

    def genre = column[String]("Genre")

    def * = (id, name, genre) <> ((Movie.apply _).tupled, Movie.unapply)
  }

  lazy val query = new TableQuery(tag => new MovieTable(tag))
}
