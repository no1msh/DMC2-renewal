package com.monthlycoding.dmc2.presenter.inquiry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.data.datasource.remote.InquiryDataSourceImpl
import com.monthlycoding.dmc2.data.remote.NetworkServiceModule
import com.monthlycoding.dmc2.data.repository.InquiryRepositoryImpl
import com.monthlycoding.domain.model.Inquiry
import com.monthlycoding.domain.repository.InquiryRepository
import kotlinx.coroutines.launch

class InquiryViewModel(
    private val inquiryRepository: InquiryRepository
) : ViewModel() {

    private val _isSuccessfulPostInquiry: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSuccessfulPostInquiry: LiveData<Boolean> get() = _isSuccessfulPostInquiry

    fun postInquiry(inquiry: Inquiry) {
        viewModelScope.launch {
            runCatching {
                inquiryRepository.postInquiry(inquiry)
            }.onSuccess {
                _isSuccessfulPostInquiry.value = true
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = InquiryRepositoryImpl(
                    InquiryDataSourceImpl(
                        NetworkServiceModule.inquiryService,
                    ),
                )
                return InquiryViewModel(repository) as T
            }
        }
    }
}
