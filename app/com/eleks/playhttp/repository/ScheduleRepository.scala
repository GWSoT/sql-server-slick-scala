package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.{Movie, Schedule}
import com.eleks.playhttp.data.models.dto.ScheduleResponse
import com.eleks.playhttp.data.models.tables.ScheduleTables.ScheduleTable
import javax.inject.{Inject, Scope}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.{JdbcProfile, SQLServerProfile}
import slick.lifted.TableQuery
import SQLServerProfile.api._
import scala.concurrent.{ExecutionContext, Future}

@Scope
class ScheduleRepository @Inject() (dbConfigProvider: DatabaseConfigProvider, movieRepository: MovieRepository)(implicit val ec: ExecutionContext)
  extends BaseRepository [ScheduleTable, Schedule](TableQuery[ScheduleTable], dbConfigProvider) {

  override val dbConfig = dbConfigProvider.get[SQLServerProfile]
  import dbConfig._
  import profile.api._


  def getSchedulesInfo: Future[Seq[ScheduleResponse]] = {
    val joinQuery = getAllQuery.join(movieRepository.getAllQuery).on(_.movieId === _.id)
    val joinRes: Future[Seq[(Schedule, Movie)]] = db.run(joinQuery.result)
    joinRes map { tupleList =>
      tupleList.map { tuple =>
        ScheduleResponse(tuple._1.time, Movie(tuple._2.id, tuple._2.name, tuple._2.genre), tuple._1.price, tuple._1.hallId)
      }
    }
  }

  def getScheduleInfo(id: Long): Future[Seq[ScheduleResponse]] = {
    val joinQuery = getAllQuery.join(movieRepository.getAllQuery).on(_.movieId === _.id).filter(_._1.id === id)
    val joinRes: Future[Seq[(Schedule, Movie)]] = db.run(joinQuery.result)
    joinRes map { tupleList =>
      tupleList.map { tuple =>
        ScheduleResponse(tuple._1.time, Movie(tuple._2.id, tuple._2.name, tuple._2.genre), tuple._1.price, tuple._1.hallId)
      }
    }
  }

  def getAllByGenre(genre: String) = {
    val joinQuery = getAllQuery.join(movieRepository.getAllQuery).on(_.movieId === _.id).filter(_._2.genre === genre)
    val joinRes: Future[Seq[(Schedule, Movie)]] = db.run(joinQuery.result)
    joinRes map { tupleList =>
      tupleList.map { tuple =>
        ScheduleResponse(tuple._1.time, Movie(tuple._2.id, tuple._2.name, tuple._2.genre), tuple._1.price, tuple._1.hallId)
      }
    }
  }
}
