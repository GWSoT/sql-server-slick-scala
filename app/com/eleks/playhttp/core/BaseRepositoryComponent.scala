package com.eleks.playhttp.core

import java.util.concurrent.Future

import slick.lifted.{CanBeQueryCondition, Rep}

trait BaseRepositoryComponent[T <: BaseTable[E], E <: BaseEntity] {
  def getById(id: Long): Future[Option[E]]
  def getAll: Future[Seq[T]]
  def filter[C <: Rep[_]](expr: T => C)(implicit wc: CanBeQueryCondition[C]): Future[Seq[E]]
  def save(row: E): Future[E]
  def deleteById(id: Long): Future[Int]
  def updateByID(id: Long, row: E): Future[E]
}
