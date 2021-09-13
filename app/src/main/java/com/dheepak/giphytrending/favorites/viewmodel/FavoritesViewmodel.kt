package com.dheepak.giphytrending.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dheepak.giphytrending.favorites.domain.FavoritesListingRepository
import com.dheepak.giphytrending.common.model.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoritesViewmodel(private val favoritesListingRepository: FavoritesListingRepository): ViewModel() {

    fun getFavorites(): Flow<PagingData<DataItem>> {
        return Pager(PagingConfig(25)) {
            favoritesListingRepository.getFavoriteGifs()
        }.flow.cachedIn(viewModelScope)
    }

    fun deleteFromFavorites(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesListingRepository.deleteFromFavorites(id)
        }
    }
}
