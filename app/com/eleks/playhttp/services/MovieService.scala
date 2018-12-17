package com.eleks.playhttp.services

import com.eleks.playhttp.data.models.Movie
import com.eleks.playhttp.repository.MovieRepository
import javax.inject.Inject

import scala.concurrent.Future

class MovieService @Inject() (movieRepository: MovieRepository) {

  def getAll = movieRepository.getAll

  def addMovie(movie: Movie): Future[Movie] = movieRepository.save(movie)
}
