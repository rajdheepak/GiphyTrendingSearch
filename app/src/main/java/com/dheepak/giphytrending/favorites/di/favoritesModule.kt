package com.dheepak.giphytrending.favorites.di

import androidx.room.Room
import com.dheepak.giphytrending.common.db.GiphyDao
import com.dheepak.giphytrending.common.db.GiphyDb
import com.dheepak.giphytrending.favorites.domain.FavoritesListingRepository
import com.dheepak.giphytrending.favorites.domain.FavoritesListingRepositoryImpl
import com.dheepak.giphytrending.favorites.viewmodel.FavoritesViewmodel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module{

    single<GiphyDao> {
        Room.databaseBuilder(get(), GiphyDb::class.java,"giphy").build().giphyDao
    }

    single<FavoritesListingRepository> {
        FavoritesListingRepositoryImpl(get())
    }

    viewModel { FavoritesViewmodel(get()) }

}