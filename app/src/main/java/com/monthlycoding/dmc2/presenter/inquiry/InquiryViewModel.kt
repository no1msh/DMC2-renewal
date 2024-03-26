package com.monthlycoding.dmc2.presenter.inquiry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.common.RemoteError
import com.monthlycoding.domain.model.Inquiry
import com.monthlycoding.domain.repository.InquiryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InquiryViewModel @Inject constructor(
    private val inquiryRepository: InquiryRepository
) : ViewModel() {

    private val _isSuccessfulPostInquiry: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSuccessfulPostInquiry: LiveData<Boolean> get() = _isSuccessfulPostInquiry

    fun postInquiry(inquiry: Inquiry, showMessage: (String) -> Unit) {
        viewModelScope.launch {
            inquiryRepository.postInquiry(inquiry)
                .onSuccess { _isSuccessfulPostInquiry.value = true }
                .onFailure {
                    _isSuccessfulPostInquiry.value = false
                    Log.d(
                        "${InquiryViewModel::class.simpleName}",
                        (it as RemoteError).toStringForDeveloper()
                    )
                    showMessage(it.toStringForUser())
                }
        }
    }
}
