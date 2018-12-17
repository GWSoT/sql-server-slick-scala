package com.eleks.playhttp.data.models

import com.eleks.playhttp.core.BaseEntity

case class Movie(id: Long = 0, name: String, genre: String) extends BaseEntity
