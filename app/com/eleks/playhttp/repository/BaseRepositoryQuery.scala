package com.eleks.playhttp.repository

import com.eleks.playhttp.core.{BaseEntity, BaseTable}
import slick.jdbc.SQLServerProfile
import slick.lifted.{CanBeQueryCondition, Rep}
import slick.jdbc.SQLServerProfile.api._


trait BaseRepositoryQuery[T <: BaseTable[E], E <: BaseEntity] {
  val query: SQLServerProfile.api.TableQuery[T]

  def getByIdQuery(id: Long) = query.filter(_.id === id)

  def getAllQuery = query

  def filterQuery[C <: Rep[_]](expr: T => C)(implicit wc: CanBeQueryCondition[C])= {
    query.filter(expr)
  }

  def saveQuery(row: E) = query returning query += row

  def deleteByIdQuery(id: Long) = query.filter(_.id === id).delete

  def updateByIdQuery(id: Long, row: E) = query.filter(_.id === id).update(row)
}
