package com.eleks.playhttp.data.models

import com.eleks.playhttp.core.BaseEntity

case class Schedule (id: Long = 0, time: String, movieId: Long, price: Double, hallId: Long) extends BaseEntity
