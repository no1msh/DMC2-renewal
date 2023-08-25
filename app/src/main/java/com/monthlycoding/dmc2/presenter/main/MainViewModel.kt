package com.monthlycoding.dmc2.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monthlycoding.dmc2.presenter.main.model.TimesOfDay
import com.monthlycoding.domain.Time
import java.time.LocalTime

class MainViewModel : ViewModel() {

    private val _greetings: MutableLiveData<TimesOfDay> = MutableLiveData()
    val greetings: LiveData<TimesOfDay> get() = _greetings

    fun initGreetings() {
        _greetings.value = TimesOfDay.of(Time.getCurrentTime(getCurrentHour()))
    }

    private fun getCurrentHour(): Int = Time.getFormattedHour { LocalTime.now() }
}
