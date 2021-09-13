package com.dheepak.giphytrending.favorites.domain

import androidx.paging.*
import com.dheepak.giphytrending.common.db.GiphyDao
import com.dheepak.giphytrending.common.model.DataItem

class FavoritesListingRepositoryImpl(private val giphyDao: GiphyDao): FavoritesListingRepository {
    override fun getFavoriteGifs(): PagingSource<Int, DataItem> {
        return giphyDao.getFavoritesGifs()
    }

    override fun deleteFromFavorites(id: String) {
        return giphyDao.deleteFavorite(id)
    }

}