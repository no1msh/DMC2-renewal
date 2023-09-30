package com.monthlycoding.dmc2.presenter.hitandmiss.hitcounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monthlycoding.domain.Counter

class HitCounterViewModel : ViewModel() {

    private val counter = Counter()

    private val _count: MutableLiveData<Int> = MutableLiveData(counter.count)
    val count: LiveData<Int> get() = _count

    private val _isEnabledDecrease: MutableLiveData<Boolean> =
        MutableLiveData(!counter.isMinCount())
    val isEnabledDecrease: LiveData<Boolean> get() = _isEnabledDecrease

    private val _isEnabledIncrease: MutableLiveData<Boolean> =
        MutableLiveData(!counter.isMaxCount())
    val isEnabledIncrease: LiveData<Boolean> get() = _isEnabledIncrease

    fun increaseCount() {
        counter.increase()
        enabledCheck()
        _count.value = counter.count
    }

    fun decreaseCount() {
        counter.decrease()
        enabledCheck()
        _count.value = counter.count
    }

    private fun enabledCheck() {
        _isEnabledIncrease.value = !counter.isMaxCount()
        _isEnabledDecrease.value = !counter.isMinCount()
    }
}
