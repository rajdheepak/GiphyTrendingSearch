package com.dheepak.giphytrending.trending.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.dheepak.giphytrending.common.model.DataItem

interface TrendingRepository {
    fun getTrendingGifs(): PagingSource<Int, DataItem>
    fun getSearchGifs(s: String): PagingSource<Int, DataItem>
    fun addToFavorites(dataItem: DataItem)
    fun getFavorites(): LiveData<List<DataItem>>
    fun getFavoritesList(): List<DataItem>
    fun deleteFromFavorites(id: String)
}