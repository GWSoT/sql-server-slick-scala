package com.eleks.playhttp.services

import com.eleks.playhttp.data.models.{Movie, Schedule}
import com.eleks.playhttp.data.models.dto.ScheduleResponse
import com.eleks.playhttp.repository.{MovieRepository, ScheduleRepository}
import javax.inject.{Inject, Scope}
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

@Scope
class ScheduleService @Inject() (scheduleRepository: ScheduleRepository, movieRepository: MovieRepository)
                                (implicit val ec: ExecutionContext) {

  def getAll = scheduleRepository.getAll

  def addSchedule(schedule: Schedule) = scheduleRepository.save(schedule)

  def getScheduleInfo(id: Long) = scheduleRepository.getById(id)

  def getSchedulesInfo: Future[Seq[ScheduleResponse]] = scheduleRepository.getSchedulesInfo

  def getAllByGenre(genre: String): Future[Seq[ScheduleResponse]] = scheduleRepository.getAllByGenre(genre)
}
