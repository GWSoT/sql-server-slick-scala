package com.eleks.playhttp.repository

import com.eleks.playhttp.data.models.tables.core.{BaseEntity, BaseTable}
import javax.inject.{Inject, Singleton}
import slick.jdbc.{JdbcProfile, SQLServerProfile}
import slick.lifted.{CanBeQueryCondition, Rep, TableQuery}

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag
import play.api.db.slick._


abstract class BaseRepository[T <: BaseTable[E], E <: BaseEntity : ClassTag] @Inject()
  (clazz: TableQuery[T], dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends BaseRepositoryQuery[T, E] with BaseRepositoryComponent[T,E] {
  val dbConfig = dbConfigProvider.get[SQLServerProfile]

  import dbConfig._
  import profile.api._


  def getAll: Future[Seq[E]] = {
    db.run(getAllQuery.result)
  }

  def getById(id: Long): Future[Option[E]] = {
    db.run(getByIdQuery(id).result.headOption)
  }

//  def filter[C <: Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]) = {
//    db.run(filterQuery(expr).result)
//  }

  def save(row: E) = {
    db.run(saveQuery(row))
  }

  def updateById(id: Long, row: E) = {
    db.run(updateByIdQuery(id, row))
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(deleteByIdQuery(id))
  }

}