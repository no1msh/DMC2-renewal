package com.monthlycoding.dmc2.presenter.foodrecommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodRecommendViewModel : ViewModel() {

    private val _isEnabledDoneButton: MutableLiveData<Boolean> = MutableLiveData()
    val isEnabledDoneButton: LiveData<Boolean> get() = _isEnabledDoneButton

    fun setIsEnabledDoneButton(isEnabled: Boolean) {
        _isEnabledDoneButton.value = isEnabled
    }
}
