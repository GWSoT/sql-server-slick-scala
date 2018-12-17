package com.eleks.playhttp.repository

import com.eleks.playhttp.core.{BaseEntity, BaseRepositoryComponent, BaseTable}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.SQLServerProfile
import slick.lifted
import slick.lifted.{CanBeQueryCondition, Rep, TableQuery}

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag
import scala.reflect._



abstract class BaseRepository[T <: BaseTable[E], E <: BaseEntity : ClassTag] @Inject()
  (clazz: lifted.TableQuery[T], dbConfigProvider: DatabaseConfigProvider)
  (implicit ec: ExecutionContext) extends BaseRepositoryQuery[T, E] {

  val clazzTable: lifted.TableQuery[T] = clazz
  lazy val clazzEntity = classTag[E].runtimeClass
  val query: SQLServerProfile.api.TableQuery[T] = clazz
  val dbConfig = dbConfigProvider.get[SQLServerProfile]
  import dbConfig._
  import profile.api._

  def getAll: Future[Seq[E]] = {
    db.run(getAllQuery.result)
  }

  def getById(id: Long): Future[Option[E]] = {
    db.run(getByIdQuery(id).result.headOption)
  }

  def filter[C <: lifted.Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]) = {
    db.run(filterQuery(expr).result)
  }

  def save(row: E): Future[E] = {
    db.run(saveQuery(row))
  }

  def updateById(id: Long, row: E) = {
    db.run(updateByIdQuery(id, row))
  }

  def deleteById(id: Long) = {
    db.run(deleteByIdQuery(id))
  }

}