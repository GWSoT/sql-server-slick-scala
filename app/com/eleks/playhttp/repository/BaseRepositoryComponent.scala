package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.tables.core.{BaseEntity, BaseTable}
import slick.lifted.{CanBeQueryCondition, Rep}
import slick.model.Table

import scala.concurrent.Future

trait BaseRepositoryComponent[T <: BaseTable[E], E <: BaseEntity] {
  def getById(id: Long) : Future[Option[E]]
  def getAll : Future[Seq[E]]
  //def filter[C <: Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]): Future[Seq[E]]
  def save(row: E) : Future[E]
  def deleteById(id: Long) : Future[Int]
  def updateById(id: Long, row: E) : Future[Int]
}