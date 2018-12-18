package com.eleks.playhttp.services

import com.eleks.playhttp.data.models.Movie
import com.eleks.playhttp.repository.{MovieRepository, ScheduleRepository}
import javax.inject.Inject
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}

class MovieService @Inject() (movieRepository: MovieRepository, scheduleRepository: ScheduleRepository)
                             (implicit val ec: ExecutionContext) {

  def getAll = movieRepository.getAll

  def addMovie(movie: Movie): Future[Int] = movieRepository.save(movie)

  def deleteMovie(id: Long) = {
    val movies = scheduleRepository.filter(_.movieId === id).map(_.length)
    movies.flatMap {
      case mvLength if mvLength <= 0 => movieRepository.deleteById(id)
      case _ => Future.successful(-1)
    }
  }

  def getById(id: Long) = movieRepository.getById(id)

}
