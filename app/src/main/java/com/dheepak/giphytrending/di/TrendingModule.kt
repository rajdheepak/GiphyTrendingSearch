package com.dheepak.giphytrending.di

import com.dheepak.giphytrending.domain.NetworkService
import com.dheepak.giphytrending.domain.TrendingRepository
import com.dheepak.giphytrending.domain.TrendingRepositoryImpl
import com.dheepak.giphytrending.viewmodel.TrendingViewModel
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val trendingModule = module{

    viewModel { TrendingViewModel(get()) }

    single<TrendingRepository> { TrendingRepositoryImpl(get()) }

    single<NetworkService> {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(NetworkService::class.java)
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(get()))
            .build()
    }

}