package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.Movie
import com.eleks.playhttp.data.models.tables.MovieTables.MovieTable
import javax.inject.{Inject, Scope}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext

@Scope
class MovieRepository @Inject() (dbConfigProvider: DatabaseConfigProvider) (implicit val ec: ExecutionContext)
  extends BaseRepository [MovieTable, Movie](TableQuery[MovieTable], dbConfigProvider)
