package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.tables.core.{BaseEntity, BaseTable}
import slick.lifted.{CanBeQueryCondition, Rep, TableQuery}
import slick.jdbc.SQLServerProfile.api._


trait BaseRepositoryQuery[T <: BaseTable[E], E <: BaseEntity] {

  val query: TableQuery[T]

  def getByIdQuery(id: Long) = {
    query.filter(_.id === id)
  }

  def getAllQuery = {
    query
  }

  def filterQuery[C <: Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]) = {
    query.filter(expr)
  }

  def saveQuery(row: E) = {
    query returning query += row
  }

  def deleteByIdQuery(id: Long) = {
    query.filter(_.id === id).delete
  }

  def updateByIdQuery(id: Long, row: E) = {
    query.filter(_.id === id).update(row)
  }

}
