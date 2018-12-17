package com.eleks.playhttp.core

import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{Rep, Tag}

import scala.reflect.ClassTag

abstract class BaseTable[E: ClassTag](tag: Tag, schemaName: Option[String], tableName: String)
  extends Table[E](tag, schemaName, tableName) {
  val id: Rep[Long] = column[Long]("Id", O.PrimaryKey, O.AutoInc)
}
