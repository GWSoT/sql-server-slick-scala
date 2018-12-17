package com.eleks.playhttp.data.models.dto

import com.eleks.playhttp.data.models.Movie

case class ScheduleResponse(time: String, movie: Movie, price: Double, hallId: Long)
