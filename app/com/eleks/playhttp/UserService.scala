package com.eleks.playhttp

import com.eleks.playhttp.data.models.User
import com.eleks.playhttp.repository.{BaseRepository, UserRepository}
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent._

class UserService @Inject()(baseRepository: UserRepository)(implicit ec: ExecutionContext) {


  def saveUser(user: User): Future[Boolean] = {
    baseRepository.save(user).map(_ => true)
  }

  def getUserById(id: Long): Future[Option[User]] = {
    baseRepository.getById(id).map(user => user)
  }

  def deleteUser(id: Long): Future[Int] = {
    baseRepository.deleteById(id)
  }

  def updateUser(oldUser: User, newUser: User): Future[Boolean] = {
    baseRepository.updateById(oldUser.id, newUser).map(_ > 0)
  }
}
