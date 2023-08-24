package com.monthlycoding.dmc2.presenter.main.model

import android.content.Context
import androidx.annotation.ArrayRes
import com.monthlycoding.dmc2.R
import com.monthlycoding.domain.Time

enum class TimesOfDay(@ArrayRes val stringsId: Int) {
    MORNING(R.array.morning),
    LUNCH(R.array.lunch),
    LATE_LUNCH(R.array.late_lunch),
    NIGHT(R.array.night),
    LATE_NIGHT(R.array.late_night),
    DAWN(R.array.dawn),
    ;

    fun getGreetings(context: Context): String =
        context.resources.getStringArray(this.stringsId).random()

    companion object {
        fun of(time: Time): TimesOfDay = when (time) {
            Time.MORNING -> MORNING
            Time.LUNCH -> LUNCH
            Time.LATE_LUNCH -> LATE_LUNCH
            Time.NIGHT -> NIGHT
            Time.LATE_NIGHT -> LATE_NIGHT
            Time.DAWN -> DAWN
        }
    }
}
