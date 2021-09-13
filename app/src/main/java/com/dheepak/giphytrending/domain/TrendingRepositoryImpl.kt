package com.dheepak.giphytrending.domain

import androidx.paging.PagingSource
import com.dheepak.giphytrending.model.DataItem

class TrendingRepositoryImpl(private val networkService: NetworkService): TrendingRepository {

    override fun getTrendingGifs(): PagingSource<Int, DataItem> {
        return object : PagingSource<Int, DataItem>() {

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
                return try {
                    val currentLoadingPageKey = params.key ?: 0
                    val response = networkService.getTrendingGifs(currentLoadingPageKey)

                    if(response.isSuccessful) {
                        val responseData: List<DataItem> = response.body()?.data ?: emptyList()

                        val prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey - 25

                        LoadResult.Page(
                            data = responseData,
                            prevKey = prevKey,
                            nextKey = currentLoadingPageKey.plus(response.body()?.pagination?.count!!)
                        )
                    } else {
                        LoadResult.Error(java.lang.RuntimeException(response.code().toString()))
                    }
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

        }
    }
}