package com.eleks.playhttp

object Database {

  var db: Set[User] = Set(
    User(1, "Tommy", "NY"),
    User(2, "John", "LA"),
    User(3, "Nick", "NY"),
    User(4, "Rob", "Detroit"),
    User(5, "Alice", "Boston"),
    User(6, "Alan", "LA")
  )

  def saveUser(user: User): Boolean = {
    val sizeBefore = db.size
    db += user
    val sizeAfter = db.size

    if (sizeBefore == sizeAfter) false
    else true
  }

  def getUserById(id: Long): Option[User]= db.find(_.id == id)
}
