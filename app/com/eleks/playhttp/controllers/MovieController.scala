package com.eleks.playhttp.controllers

import com.eleks.playhttp.data.models.Movie
import com.eleks.playhttp.services.MovieService
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.InjectedController

import scala.concurrent.ExecutionContext
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
    movieService.addMovie(movie).map(f => Created(Json.toJson(f)))
  }
}
