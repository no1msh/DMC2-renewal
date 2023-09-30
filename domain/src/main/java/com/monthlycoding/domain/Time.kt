package com.monthlycoding.domain

import java.time.format.DateTimeFormatter

enum class Time {
    MORNING,
    LUNCH,
    LATE_LUNCH,
    NIGHT,
    LATE_NIGHT,
    DAWN,
    ;

    companion object {
        private const val HOUR_FORMAT = "H"
        private const val MORNING_START = 6
        private const val LUNCH_START = 11
        private const val LATE_LUNCH_START = 14
        private const val NIGHT_START = 18
        private const val LATE_NIGHT_START = 21
        private const val LATE_NIGHT_END = 23
        private const val DAWN_START = 0

        fun getFormattedHour(timeGenerator: CurrentTimeGenerator): Int =
            timeGenerator.getCurrentTime().format(DateTimeFormatter.ofPattern(HOUR_FORMAT)).toInt()

        fun getCurrentTime(hour: Int) = when (hour) {
            in (MORNING_START until LUNCH_START) -> MORNING
            in (LUNCH_START until LATE_LUNCH_START) -> LUNCH
            in (LATE_LUNCH_START until NIGHT_START) -> LATE_LUNCH
            in (NIGHT_START until LATE_NIGHT_START) -> NIGHT
            in (LATE_NIGHT_START..LATE_NIGHT_END) -> LATE_NIGHT
            in (DAWN_START until MORNING_START) -> DAWN
            else -> throw IllegalArgumentException("$hour : 잘못된 시간 입력입니다.")
        }
    }
}
