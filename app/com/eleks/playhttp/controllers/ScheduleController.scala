package com.eleks.playhttp.controllers

import com.eleks.playhttp.data.models.dto.ScheduleResponse
import com.eleks.playhttp.data.models.{Movie, Schedule}
import com.eleks.playhttp.services.ScheduleService
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.InjectedController

import scala.concurrent.ExecutionContext

class ScheduleController @Inject() (scheduleService: ScheduleService)
                                   (implicit val ec: ExecutionContext)
  extends InjectedController {

  implicit val movieJson = Json.format[Movie]
  implicit val scheduleJson = Json.format[Schedule]
  implicit val scheduleResponseJson = Json.format[ScheduleResponse]
  
  def getAll (genre: String) = Action.async {
    if (!genre.isEmpty) {
      scheduleService.getAllByGenre(genre).map {
        result => Ok(Json.obj("Schedules" -> result))
      }
    } else {
      scheduleService.getSchedulesInfo.map {
        result => Ok(Json.obj("Schedules" -> result))
      }
    }
  }

  def addSchedule = Action.async(parse.json) {request =>
    val schedule = request.body.as[Schedule]
    scheduleService.addSchedule(schedule).map {
      case result if result > 0 => Created(Json.toJson(schedule))
      case _ => NotAcceptable("Internal error occurred.")
    }
  }

  def getById(id: Long) = Action.async {
    scheduleService.getScheduleInfo(id).map {
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound(s"Schedule with id $id not found")
    }
  }

  def delete(id: Long) = Action.async {
    scheduleService.deleteById(id).map {
      result => Ok(Json.toJson(result))
    }
  }
}
