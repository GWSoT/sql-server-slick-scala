package com.eleks.playhttp

import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{InjectedController, _}

class Controller @Inject() extends InjectedController {

  implicit val userFormat = Json.format[User]

  def getUser(id: Long) = Action {
    val userOpt: Option[User] = Service.getUserById(id)
    userOpt match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(s"User with id = $id does not exist")
    }
  }

  def saveUser(): Action[JsValue] = Action(parse.json) { request =>
    val user = request.body.as[User]
    val result = Service.saveUser(user)

    if (result) {
      Created("User was created")
    } else {
      NotAcceptable("User already exists")
    }

  }

}
