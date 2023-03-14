package com.sindrenm.lwb.app.home

import com.sindrenm.lwb.app.home.Weight.Companion.kg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class RecordsRepository @Inject constructor() {
    private var records = listOf(
        Record("Barbell Deadlift", LocalDate.parse("2022-02-17"), 140.kg, "Social Lift"),
        Record("Barbell Bench Press", LocalDate.parse("2022-02-22"), 95.kg, "With PT"),
        Record("Barbell Deadlift", LocalDate.parse("2022-02-27"), 140.kg, "Failed 150, post-squats"),
        Record("Barbell Squat", LocalDate.parse("2022-02-27"), 120.kg, notes = null),
        Record("Barbell Deadlift", LocalDate.parse("2022-03-09"), 160.kg, "With a belt"),
        Record("Barbell Squat", LocalDate.parse("2022-03-13"), 130.kg, "Alone"),
        Record("Barbell Deadlift", LocalDate.parse("2022-03-14"), 140.kg, "Failed 160"),
    )

    fun getRecords(): Flow<List<Record>> {
        return flow {
            delay(1000) // simulate latency

            emit(records.sortedBy { it.date })
        }
    }
}
