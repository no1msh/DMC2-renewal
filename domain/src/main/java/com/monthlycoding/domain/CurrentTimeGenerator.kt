package com.monthlycoding.domain

import java.time.LocalTime

fun interface CurrentTimeGenerator {
    fun getCurrentTime(): LocalTime
}
