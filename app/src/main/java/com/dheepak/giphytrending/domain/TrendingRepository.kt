package com.dheepak.giphytrending.domain

import androidx.paging.PagingSource
import com.dheepak.giphytrending.model.DataItem

interface TrendingRepository {
    fun getTrendingGifs(): PagingSource<Int, DataItem>
}