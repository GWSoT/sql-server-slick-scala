package com.eleks.playhttp

import com.eleks.playhttp.data.models.User
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{InjectedController, _}
import scala.concurrent._



class Controller @Inject() (userService: UserService)(implicit ec: ExecutionContext) extends InjectedController {

  implicit val userFormat = Json.format[User]

  def getUser(id: Long) = Action.async {
    userService.getUserById(id).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(s"User with id = $id does not found.")
    }
  }

  def saveUser(): Action[JsValue] = Action(parse.json).async { request =>
    val user = request.body.as[User]
    userService.saveUser(user).map {
      case true => Created("User was created")
    }
  }

  def deleteUser(id: Long) = Action {
    userService.deleteUser(id)
    Ok("User was deleted")
  }

//  def updateUser(id: Long) = Action(parse.json).async {
//    request =>
//      var user = request.body.as[User]
//      Service.updateUser(user, user)
//      Ok("Okay")
//  }
}
