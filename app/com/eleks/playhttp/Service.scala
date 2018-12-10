package com.eleks.playhttp

object Service {

  def saveUser(user: User): Boolean = Database.saveUser(user)

  def getUserById(id: Long): Option[User] = Database.getUserById(id)

}
