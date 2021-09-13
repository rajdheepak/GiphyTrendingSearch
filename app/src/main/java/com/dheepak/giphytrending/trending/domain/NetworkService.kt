package com.dheepak.giphytrending.trending.domain

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("trending?api_key=o4W7p9GgvVz4CtQNh7HrIg8V1wYWydMb")
    suspend fun getTrendingGifs(@Query("offset") offset: Int): Response<com.dheepak.giphytrending.common.model.ApiResponse>
}