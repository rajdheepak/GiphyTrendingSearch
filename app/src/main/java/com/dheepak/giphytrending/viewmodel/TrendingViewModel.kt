package com.dheepak.giphytrending.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dheepak.giphytrending.domain.TrendingRepository

class TrendingViewModel(private val trendingRepository: TrendingRepository): ViewModel() {

    val trendingGifsData = Pager(PagingConfig(25)) {
        trendingRepository.getTrendingGifs()
    }.flow.cachedIn(viewModelScope)

}