package com.dheepak.giphytrending.trending.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dheepak.giphytrending.trending.domain.TrendingRepository
import com.dheepak.giphytrending.common.model.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingViewModel(private val trendingRepository: TrendingRepository): ViewModel() {

    val trendingGifsData = Pager(PagingConfig(25)) {
        trendingRepository.getTrendingGifs()
    }.flow.cachedIn(viewModelScope)

    fun performFavoriteAction(dataItem: DataItem) {
        viewModelScope.launch(Dispatchers.IO) {
            if(trendingRepository.getFavoritesList().contains(dataItem)) {
                trendingRepository.deleteFromFavorites(dataItem.id)
            } else {
                trendingRepository.addToFavorites(dataItem)
            }
        }
    }

    fun favoritesData(): LiveData<List<DataItem>> = trendingRepository.getFavorites()

}