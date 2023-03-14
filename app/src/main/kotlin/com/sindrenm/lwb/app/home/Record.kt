package com.sindrenm.lwb.app.home

import kotlinx.datetime.LocalDate

data class Record(
    val exercise: String,
    val date: LocalDate,
    val weight: Weight,
    val notes: String?,
)
