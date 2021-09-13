package com.dheepak.giphytrending.favorites.domain

import androidx.paging.PagingSource
import com.dheepak.giphytrending.common.model.DataItem

interface FavoritesListingRepository {
    fun getFavoriteGifs(): PagingSource<Int, DataItem>
    fun deleteFromFavorites(id: String)
}