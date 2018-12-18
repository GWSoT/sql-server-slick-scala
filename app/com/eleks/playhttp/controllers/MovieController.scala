package com.eleks.playhttp.controllers

import com.eleks.playhttp.data.models.Movie
import com.eleks.playhttp.services.MovieService
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.InjectedController

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}
import scala.util.Success

class MovieController @Inject() (movieService: MovieService)(implicit val ec: ExecutionContext) extends InjectedController{

  implicit val movieJson = Json.format[Movie]

  def getAll = Action.async {
    movieService.getAll.map {
      movies => Ok(Json.obj("Movies" -> movies))
    }
  }

  def addMovie = Action.async(parse.json) { request =>
    val movie = request.body.as[Movie]
    movieService.addMovie(movie).map {
      case result if result > 0 => Created(Json.toJson(movie))
      case _ => NotAcceptable("Internal error occurred.")
    }
  }

  def getById(id: Long) = Action.async {
    movieService.getById(id).map {
      res => Ok(Json.toJson(res))
//      case Some(res) => Ok(Json.toJson(res))
//      case None => NotFound(s"User with id $id not found.")
    }
  }

  def delete(id: Long) = Action.async {
    movieService.deleteMovie(id).map {
      case f if f > 0 => Created(s"Successfully deleted entity with id $id")
      case _ => NotFound(s"Internal error")
    }
  }
}
