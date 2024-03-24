package com.monthlycoding.dmc2.presenter.cardnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.data.mapper.toUIModel
import com.monthlycoding.dmc2.presenter.cardnews.model.CardNewsUIModel
import com.monthlycoding.domain.model.CardNews
import com.monthlycoding.domain.repository.CardNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardNewsViewModel @Inject constructor(
    private val cardNewsRepository: CardNewsRepository
) : ViewModel() {

    private val _allCardNews: MutableLiveData<List<CardNewsUIModel>> = MutableLiveData()
    val allCardNews: LiveData<List<CardNewsUIModel>> get() = _allCardNews

    fun getAllCardNews() {
        viewModelScope.launch {
            cardNewsRepository.getAllCardNews()
                .onSuccess {
                    _allCardNews.value = it.map(CardNews::toUIModel)
                }
                .onFailure {
                    Log.d("NetworkError", it.message.toString())
                }
        }
    }
}
