package com.monthlycoding.dmc2.presenter.cardnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.data.datasource.remote.CardNewsDataSourceImpl
import com.monthlycoding.dmc2.data.mapper.toUIModel
import com.monthlycoding.dmc2.data.remote.NetworkServiceModule
import com.monthlycoding.dmc2.data.repository.CardNewsRepositoryImpl
import com.monthlycoding.dmc2.presenter.cardnews.model.CardNewsUIModel
import com.monthlycoding.domain.repository.CardNewsRepository
import kotlinx.coroutines.launch

class CardNewsViewModel(
    private val cardNewsRepository: CardNewsRepository
) : ViewModel() {

    private val _allCardNews: MutableLiveData<List<CardNewsUIModel>> = MutableLiveData()
    val allCardNews: LiveData<List<CardNewsUIModel>> get() = _allCardNews

    fun getAllCardNews() {
        viewModelScope.launch {
            runCatching {
                cardNewsRepository.getAllCardNews()
            }.onSuccess { allCardNews ->
                _allCardNews.value = allCardNews.map { it.toUIModel() }
            }
        }
    }

    fun changeCardNewsReadMoreState(cardNews: CardNewsUIModel) {
        val targetIndex: Int = _allCardNews.value?.indexOf(cardNews) ?: -1
        if (targetIndex != -1) {
            val mutableCardNews: MutableList<CardNewsUIModel> =
                _allCardNews.value!!.toMutableList()
            mutableCardNews[targetIndex] = cardNews.copy(isReadMoreCollapse = false)
            _allCardNews.value = mutableCardNews.toList()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = CardNewsRepositoryImpl(
                    CardNewsDataSourceImpl(
                        NetworkServiceModule.cardNewsService,
                    ),
                )
                return CardNewsViewModel(repository) as T
            }
        }
    }
}
